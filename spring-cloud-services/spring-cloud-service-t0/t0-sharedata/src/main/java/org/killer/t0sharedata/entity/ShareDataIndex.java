package org.killer.t0sharedata.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author killer
 * @date 2020/05/31 - 12:25
 */
@Data
@Accessors(chain = true)
@Entity
public class ShareDataIndex extends ShareData {

    /** 换手率 */
    private BigDecimal turnoverRate;

    /** 换手率（自由流通股） */
    private BigDecimal turnoverRateF;

    /** 量比 */
    private BigDecimal volumeRatio;

    /** pe */
    private BigDecimal peTTM;

    /** pb 市净率 */
    private BigDecimal Pb;

    /** 总股本(万) */
    private BigDecimal totalShare;

    /** 流通股本 */
    private BigDecimal floatShare;

    /** 自由流通股本 */
    private BigDecimal freeShare;

    /** 总市值 */
    private BigDecimal totalMv;

    /** 流通市值(公司老板可以通过出售自己的股票来增加流通市值) */
    private BigDecimal circMv;

}
