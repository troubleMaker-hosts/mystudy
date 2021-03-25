package com.example.demo.convert;

import com.example.demo.model.Student;
import com.example.demo.model.StudyUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @ClassName: StudentConverter
 * @Description:    student 转 studyUser
 * @Author: willie
 * @version: 1.0.0
 * @Date: 2021/03/25 15:40
 * @Copyright: Copyright(c)2021 willie All Rights Reserved
 */
@Mapper
public interface StudentConverter {
    StudentConverter INSTANCE = Mappers.getMapper(StudentConverter.class);

    /**
     * student 转 StudyUser
     * @param student   student
     * @return  StudyUser
     */
    StudyUser studentToStudyUser(Student student);

    /**
     * List<Student> 转 List<StudyUser>
     * @param studentList   List<Student>
     * @return  List<StudyUser>
     */
    List<StudyUser> studentListToStudyUserList(List<Student> studentList);


}
