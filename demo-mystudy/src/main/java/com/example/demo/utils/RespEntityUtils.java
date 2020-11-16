package com.example.demo.utils;

import com.example.demo.model.RespEntity;

/**
 * @ClassName: RespEntityUtils
 * @Description: controller层 返回结果 的 工具类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/21 03:26
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class RespEntityUtils {
    /**
     * 创建一个 RespEntity 对象
     * @param code  RespEntity的状态码
     * @param msg   RespEntity的消息
     * @param dataInfo  RespEntity的数据
     * @param <T>   RespEntity的数据的类型
     * @return  RespEntity 对象
     */
    private static <T> RespEntity buildRespEntity(Integer code, String msg, T dataInfo) {
        RespEntity<T> resp = new RespEntity<T>();
        resp.setCode(code);
        resp.setMsg(msg);
        resp.setDataInfo(dataInfo);
        return resp;
    }

    /**
     * 创建只有有状态码的RespEntity
     * @param code  状态码
     * @return  只有状态码的RespEntity
     */
    public static RespEntity buildResp(int code) {
        return buildRespEntity(code, null, null);
    }

    /**
     * 创建有状态码和msg的RespEntity
     * @param code  状态码
     * @param msg   消息
     * @return  有状态码和msg的RespEntity
     */
    public static RespEntity buildResp(int code, String msg) {
        return buildRespEntity(code, msg, null);
    }

    /**
     * 创建有 dataInfo 和 code 的RespEntity
     * @param code 状态码
     * @param dataInfo 返回结果数据
     * @param <T> 结果数据类型
     * @return  dataInfo 和 code 的RespEntity
     */
    public static <T> RespEntity buildResp(int code, T dataInfo) {
        return buildRespEntity(code, null, dataInfo);
    }

    /**
     * 创建有 code 和 code 和  dataInfo 的RespEntity
     * @param code  状态码
     * @param msg   消息
     * @param dataInfo  返回结果数据
     * @param <T>   结果数据类型
     * @return  code 和 code 和  dataInfo 的RespEntity
     */
    public static <T> RespEntity buildResp(int code, String msg, T dataInfo) {
        return buildRespEntity(code, msg, dataInfo);
    }

    /**
     * 创建 状态码 为 1 (成功) 的 RespEntity
     * @return 状态码 为 1 (成功) 的 RespEntity
     */
    public static RespEntity buildSuccResp() {
        return buildRespEntity(RespEntity.SUCCESS_CODE, null, null);
    }

    /**
     * 创建 状态码 为 1 (成功) 和 有 msg 的 RespEntity
     * @param msg   消息
     * @return  状态码 为 1 (成功) 和 有 msg 的 RespEntity
     */
    public static RespEntity buildSuccResp(String msg) {
        return buildRespEntity(RespEntity.SUCCESS_CODE, msg, null);
    }

    /**
     * 创建 状态码 为 1 (成功) 和 有 dataInfo 的 RespEntity
     * @param dataInfo  返回结果数据
     * @param <T>   结果数据类型
     * @return  状态码 为 1 (成功) 和 有 dataInfo 的 RespEntity
     */
    public static <T> RespEntity buildSuccResp(T dataInfo) {
        return buildRespEntity(RespEntity.SUCCESS_CODE, null, dataInfo);
    }

    /**
     * 创建 状态码 为 1 (成功) 和 有 dataInfo 和 msg 的 RespEntity
     * @param msg   消息
     * @param dataInfo  返回结果数据
     * @param <T>   结果数据类型
     * @return  状态码 为 1 (成功) 和 有 dataInfo 和 msg 的 RespEntity
     */
    public static <T> RespEntity buildSuccResp(String msg, T dataInfo) {
        return buildRespEntity(RespEntity.FAIL_CODE, msg, dataInfo);
    }

    /**
     * 创建 状态码 为 0 (失败) 的 RespEntity
     * @return 状态码 为 0 (失败) 的 RespEntity
     */
    public static RespEntity buildFailResp() {
        return buildRespEntity(RespEntity.FAIL_CODE, null, null);
    }

    /**
     * 创建 状态码 为 0 (失败) 和 有 msg 的 RespEntity
     * @param msg   消息
     * @return  状态码 为 0 (失败) 和 有 msg 的 RespEntity
     */
    public static RespEntity buildFailResp(String msg) {
        return buildRespEntity(RespEntity.FAIL_CODE, msg, null);
    }

    /**
     * 创建 状态码 为 0 (失败) 和 有 dataInfo 的 RespEntity
     * @param dataInfo  返回结果数据
     * @param <T>   结果数据类型
     * @return  状态码 为 0 (失败) 和 有 dataInfo 的 RespEntity
     */
    public static <T> RespEntity buildFailResp(T dataInfo) {
        return buildRespEntity(RespEntity.FAIL_CODE, null, dataInfo);
    }

    /**
     * 创建 状态码 为 0 (失败) 和 有 dataInfo 和 msg 的 RespEntity
     * @param msg   消息
     * @param dataInfo  返回结果数据
     * @param <T>   结果数据类型
     * @return  状态码 为 0 (失败) 和 有 dataInfo 和 msg 的 RespEntity
     */
    public static <T> RespEntity buildFailResp(String msg, T dataInfo) {
        return buildRespEntity(RespEntity.FAIL_CODE, msg, dataInfo);
    }
}
