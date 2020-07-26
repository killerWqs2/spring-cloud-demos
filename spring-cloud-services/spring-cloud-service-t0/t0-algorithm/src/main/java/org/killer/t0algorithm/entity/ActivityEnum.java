package org.killer.t0algorithm.entity;

import java.math.BigDecimal;

/**
 * @author killer
 * @date 2020/05/31 - 13:01
 */
public enum  ActivityEnum {

    /** 活跃 */
    VERY_ACTIVE("非常活跃", BigDecimal.valueOf(10), null),

    /** 活跃性一般*/
    NORMAL_ACTIVE("一般活跃", BigDecimal.valueOf(5), null),

    /** 活跃性较低*/
    LOW_ACTIVE("活跃性较低", BigDecimal.valueOf(1.5), null),

    /** 不活跃 */
    NOT_ACTIVE("不活跃", BigDecimal.valueOf(0), null);

    private String label;

    private BigDecimal standard;

    private BigDecimal value;

    ActivityEnum(String label, BigDecimal standard, BigDecimal value) {
        this.label = label;
        this.standard = standard;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getStandard() {
        return standard;
    }

    public void setStandard(BigDecimal standard) {
        this.standard = standard;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
