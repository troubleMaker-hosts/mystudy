package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: BaseEntity
 * @Description:    基础Entity
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/09/23 03:21
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     * 此处用 transient 关键字修饰, 不会对该字段序列化和反序列化,
     * 注意 不能用 @Transient(org.springframework.data.annotation)
     */
    private transient LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否有效 (1: 有效, 0 : 无效)
     */
    private Integer isValid;

    /**
     * 备注
     */
    private String remarks;

}
