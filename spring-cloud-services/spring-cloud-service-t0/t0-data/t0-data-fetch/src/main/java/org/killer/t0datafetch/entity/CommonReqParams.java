package org.killer.t0datafetch.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author killer
 * @date 2020/05/26 - 21:23
 */
@Data
@Accessors(chain = true)
public class CommonReqParams {

    private String api_name;

    private String token;

    private Object params;

    private String fields;

}
