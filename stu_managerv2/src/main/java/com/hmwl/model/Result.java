package com.hmwl.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 封装返回数据javabean
 *
 **/
@ApiModel(value = "Result",description = "通用返回对象")
public class Result {
    public final static String MSG_SUCCESS = "success";

    @ApiModelProperty(value = "调用是否正常",name = "isok")
    private boolean isok;
    @ApiModelProperty(value = "接口返回msg，如果isok是false，那么msg是错误信息",name = "msg")
    private String msg;
    @ApiModelProperty(value = "调用成功，返回的数据",name = "data")
    private Object data;

    public Result() {
    }

    public Result(boolean isok, String msg) {
        this.isok = isok;
        this.msg = msg;
    }

    public Result(boolean isok, String msg, Object data) {
        this.isok = isok;
        this.msg = msg;
        this.data = data;
    }

    public boolean isIsok() {
        return isok;
    }

    public void setIsok(boolean isok) {
        this.isok = isok;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}