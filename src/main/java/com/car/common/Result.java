package com.car.common;

import lombok.Data;

@Data
public class Result<T> {
    // 状态码 200 成功 400 失败 500 异常
    private int code;

    // 状态信息
    private String msg;

    // 总数
    private long total;

    // 数据
    private T data;

    // Private constructor to enforce usage of static factory methods
    private Result() {}

    /**
     * Create a success result without data.
     *
     * @return Success result
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * Create a success result with data.
     *
     * @param data The data to include in the result
     * @return Success result with data
     */
    public static <T> Result<T> success(T data) {
        return success(0, data);
    }

    /**
     * Create a success result with total count and data.
     *
     * @param total The total count of data
     * @param data  The data to include in the result
     * @return Success result with total count and data
     */
    public static <T> Result<T> success(long total, T data) {
        return result(200, "success", total, data);
    }

    /**
     * Create a failure result with a specific message.
     *
     * @param msg The failure message
     * @return Failure result
     */
    public static <T> Result<T> fail(String msg) {
        return result(400, msg, 0, null);
    }

    // Private method to create a result instance
    private static <T> Result<T> result(int code, String msg, long total, T data) {
        Result<T> res = new Result<>();
        res.setCode(code);
        res.setMsg(msg);
        res.setTotal(total);
        res.setData(data);
        return res;
    }
}
