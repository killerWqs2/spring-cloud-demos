package org.killer.t0sharedata.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author killer
 * @date 2020/05/26 - 20:32
 */
@Data
@Entity
public class ShareData implements Serializable {

    /** 股票代码 */
    @Id
    private String tsCode;

    /** 交易日期 */
    private String tradeDate;

    /** 开盘价 */
    private BigDecimal open;

    /** 收盘价 */
    private BigDecimal close;

    /** 最高价 */
    private BigDecimal high;

    /** 最低价 */
    private BigDecimal low;

    /** 昨收价 */
    private BigDecimal preClose;

    /** 涨跌额*/
    private BigDecimal change;

    /** 涨跌幅 */
    private BigDecimal pctChg;

    /** 成交量 */
    private BigDecimal vol;

    /** 成交额*/
    private BigDecimal amount;

    @Override
    public String toString() {
        return "ShareData{" +
                "tsCode='" + tsCode + '\'' +
                ", tradeDate='" + tradeDate + '\'' +
                ", open=" + open +
                ", close=" + close +
                ", heigh=" + high +
                ", low=" + low +
                ", preClose=" + preClose +
                ", change=" + change +
                ", pctChg=" + pctChg +
                ", vol=" + vol +
                ", amount=" + amount +
                '}';
    }
}
