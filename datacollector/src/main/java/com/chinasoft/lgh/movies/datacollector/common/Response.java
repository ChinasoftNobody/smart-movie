package com.chinasoft.lgh.movies.datacollector.common;

/**
 * @author Administrator
 */
public class Response<T> {

    public static final String SUCCESS = "success";

    private T result;
    private boolean success;
    private String message;

    public Response(T result, boolean success, String message) {
        this.result = result;
        this.success = success;
        this.message = message;
    }

    public Response(T result) {
        this(result,true,null);
    }

    public Response(String message) {
        this(null,false,message);
    }

    public Response(T result, boolean success) {
        this(result,success,null);
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
