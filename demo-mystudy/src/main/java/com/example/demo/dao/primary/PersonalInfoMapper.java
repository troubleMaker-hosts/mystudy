package com.example.demo.dao.primary;

import com.example.demo.model.PersonalInfo;

public interface PersonalInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonalInfo record);

    int insertSelective(PersonalInfo record);

    PersonalInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonalInfo record);

    int updateByPrimaryKey(PersonalInfo record);
}