package com.example.demo.dao.primary;

import com.example.demo.model.PersonalInfo;

import java.util.List;

public interface PersonalInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(PersonalInfo record);

    PersonalInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonalInfo record);

    /**
     * 根据 isValid 查找 所有 PersonalInfo
     * @param isValid   是否有效(1:有效; 0:无效)
     * @return  List<PersonalInfo>
     */
    List<PersonalInfo> findAllByIsValid(Integer isValid);
}