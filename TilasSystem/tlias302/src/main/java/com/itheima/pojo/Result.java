package com.itheima.pojo;

/**
 * 统一响应结果封装类,无论什么请求必须响应这个类的对象
 */
public class Result {
    private Integer code ;//1 成功 , 0 失败
    private String msg; //提示信息
    //Object所有类的顶层父类，可存储任何类型
    private Object data; //数据 data

    public Result() {
    }
    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
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
    //增删改
    public static Result success(Object data){
        return new Result(1, "success", data);
    }
    //查询
    public static Result success(){
        return new Result(1, "success", null);
    }
    //响应失败
    public static Result error(String msg){
        return new Result(0, msg, null);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
