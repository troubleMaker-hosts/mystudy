package com.example.demo.convert;

import com.example.demo.model.Student;
import com.example.demo.model.StudyUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
@Mapper(componentModel="spring")
public interface StudentConverter {
    StudentConverter INSTANCE = Mappers.getMapper(StudentConverter.class);

    //target 和 source 字段名如果一样, 可以不用写(会自动映射)
    //@Mapping(target = "sex", source = "sex")
    //@Mapping(target = "userId", source = "id")
    //@Mapping(target = "userName", source = "name")

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


    /**
     * StudyUser 转 student
     * @param studyUser   studyUser
     * @return  Student
     */
    Student studyUserToStudent(StudyUser studyUser);

    /**
     * List<StudyUser> 转 List<Student>
     * @param studentList   List<StudyUser>
     * @return  List<Student>
     */
    List<Student> studyUserListToStudentList(List<StudyUser> studentList);

}
