package org.killer.t0sharedata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author killer
 * @date 2020/05/26 - 20:50
 */
@Data
@Accessors(chain = true)
public class ShareDataReqParams {

    private String ts_code;

    @JsonFormat(pattern = "YYYYMMDD")
    private String trade_date;

    @JsonFormat(pattern = "YYYYMMDD")
    private String start_date;

    @JsonFormat(pattern = "YYYYMMDD")
    private String end_date;

}
