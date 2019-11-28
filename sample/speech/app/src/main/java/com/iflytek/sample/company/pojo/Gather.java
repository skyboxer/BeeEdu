package com.iflytek.sample.company.pojo;

public class Gather {
    Result  result;
    Integer error_code;
    String  error_msg;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    @Override
    public String toString() {
        return "Gather{" +
                "result=" + result +
                ", error_code=" + error_code +
                ", error_msg='" + error_msg + '\'' +
                '}';
    }
}
