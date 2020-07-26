package org.killer.t0datafetch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author killer
 * @date 2020/5/27 -  23:40
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TuShareApi {

    /** 股票数据获取接口 */
    LISTED_COMPANY_API("stock_basic", "2b28c98d0396fbb6c3fe1c5904352a405da36d8fc1d4ac415ed13d30", null, "ts_code,symbol,name,area,industry,fullname,enname,market,exchange,curr_type,list_status,list_date,delist_date,is_hs"),

    /** 股票最新数据获取接口 */
    SHARE_NEW_API("daily", "2b28c98d0396fbb6c3fe1c5904352a405da36d8fc1d4ac415ed13d30", null, "ts_code,name"),

    /** 股票指数数据获取接口 参考文档: https://tushare.pro/document/2?doc_id=32 */
    SHARE_INDEX_API("daily_basic", "2b28c98d0396fbb6c3fe1c5904352a405da36d8fc1d4ac415ed13d30", null, "ts_code,trade_date,turnover_rate,turnover_rate_f,volume_ratio,pe_ttm,total_share,float_share,free_share,total_mv,circ_mv"),

    /** 股票概念板块接口 */
    SHARE_CONCEPT_CATEGORY_API("concept", "2b28c98d0396fbb6c3fe1c5904352a405da36d8fc1d4ac415ed13d30", null, "code,name,src"),

    /** 股票概念明细接口 */
    SHARE_CONCEPT_CATEGORY_DETAIL_API("concept_detail", "2b28c98d0396fbb6c3fe1c5904352a405da36d8fc1d4ac415ed13d30", null, "id,ts_code,in_date,out_date");

    @JsonProperty("api_name")
    private String apiName;

    private String token;

    private Object params;

    private String fields;

    TuShareApi(String apiName, String token, Object params, String fields) {
        this.apiName = apiName;
        this.token = token;
        this.params = params;
        this.fields = fields;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

}
