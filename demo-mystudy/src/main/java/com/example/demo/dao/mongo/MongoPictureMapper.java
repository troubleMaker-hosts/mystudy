package com.example.demo.dao.mongo;

import com.example.demo.model.Picture;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ClassName: MongoPictureMapper
 * @Description:    MongoPictureMapper
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2021/02/10 01:57
 * @Copyright: Copyright(c)2021 kk All Rights Reserved
 */
public interface MongoPictureMapper extends MongoRepository<Picture, Integer> {
}
