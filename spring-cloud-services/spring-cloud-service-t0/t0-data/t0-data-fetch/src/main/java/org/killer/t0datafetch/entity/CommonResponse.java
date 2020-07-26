package org.killer.t0datafetch.entity;

import lombok.Data;

/**
 * @author killer
 * @date 2020/05/26 - 21:51
 */
@Data
public class CommonResponse<T> {

    private String code;

    private String msg;

    private T data;

    @Override
    public String toString() {
        return "CommonResponse{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
