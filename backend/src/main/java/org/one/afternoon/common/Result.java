package org.one.afternoon.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 规范请求交互数据格式
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
public class Result<T> {
    // 只用于controller返回
    private Integer code;   //编码：20000 成功，0和其它数字为失败

    private String message; //错误/成功信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    /**
     * success
     *
     * @param object 封装数据
     * @param <T>    应对多种参数类型
     * @return 响应数据
     */
    public static <T> Result<T> success(T object) {
        Result<T> r = new Result<>();
        r.data = object;
        r.code = 20000;
        return r;
    }

    /**
     * error
     *
     * @param msg 报错信息
     * @param <T> 应对多种参数类型
     * @return 响应数据
     */
    public static <T> Result<T> error(String msg) {
        Result r = new Result();
        r.message = msg;
        r.code = 0;
        return r;
    }

    /**
     * add
     *
     * @param key   键名
     * @param value 键值
     * @return 封装的数据
     */
    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
