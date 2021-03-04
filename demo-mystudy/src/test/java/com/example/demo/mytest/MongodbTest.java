package com.example.demo.mytest;

import com.example.demo.DemoMystudyApplication;
import com.example.demo.dao.mongo.MongoPictureMapper;
import com.example.demo.model.Picture;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: MongodbTest
 * @Description:    Mongodb crud test
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2021/02/10 01:40
 * @Copyright: Copyright(c)2021 kk All Rights Reserved
 */
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoMystudyApplication.class})
public class MongodbTest {
    @Autowired
    private MongoPictureMapper mongoPictureMapper;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void mongoSaveTest() {
        List<Picture> pictures = getPictureList(2);
        pictures.forEach(picture -> log.info(picture.toString()));
        mongoPictureMapper.saveAll(pictures);
    }

    /**
     * 获取 List<Picture>
     * @param num   List num
     * @return  List<Picture>
     */
    private List<Picture> getPictureList(int num) {
        List<Picture> pictures = new ArrayList<>(num);
        File file = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("static/temp/background.jpg")).getPath());
        for (int i= 0; i < num; i ++) {
            try {
                pictures.add(Picture .builder()
                        .id(i + 1)
                        .pictureName(file.getName().split("\\.")[0])
                        .pictureType(file.getName().split("\\.")[1])
                        .pictureContent(FileUtils.readFileToByteArray(file))
                        .storageType(1)
                        .createTime(new Date())
                        .createUser("kk")
                        .isValid(1)
                        .build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pictures;
    }

}
