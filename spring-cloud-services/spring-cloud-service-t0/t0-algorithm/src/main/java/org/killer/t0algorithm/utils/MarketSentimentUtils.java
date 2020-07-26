package org.killer.t0algorithm.utils;

import org.killer.t0algorithm.entity.ActivityEnum;
import org.killer.t0algorithm.entity.SentimentEnum;
import org.killer.t0algorithm.utils.annotation.Version;
import org.killer.t0sharedata.entity.ShareDataIndex;

import java.math.BigDecimal;

/**
 * 分析当前市场情绪
 *
 * @author killer
 * @date 2020/05/29 - 17:42
 */
@Version(value = "v1.0.0")
public class MarketSentimentUtils {

    private MarketSentimentUtils() {}

    /** 情绪判断，感觉还需要结合换手率来判断 */
    public static SentimentEnum isVenus (ShareDataIndex data) {
        // v1.0.0 通过价格判断
        if(data.getHigh().compareTo(data.getLow()) > 0) {
            // 红色, 至少说明当前价格被市场看好
            // 计算比例，比较好
            BigDecimal totalRange = data.getHigh().subtract(data.getLow());
            BigDecimal OCRange = data.getClose().subtract(data.getOpen());

            BigDecimal UpperShadow = data.getHigh().subtract(data.getClose());
            BigDecimal belowShadow = data.getOpen().subtract(data.getLow());

            // 感觉返回之后触发条件去校验，校验范围两个月。这是一个长久的过程。


        } else {
            // 绿色，至少说明当前价格被市场看衰

        }

        return null;

    }

    /** 活跃性判断， 目前仅仅根据换手率来判断 */
    public static ActivityEnum isActive(ShareDataIndex data) {
        // v1.0.0 通过换手率来判断
        if(data.getTurnoverRateF().compareTo(ActivityEnum.LOW_ACTIVE.getStandard()) < 0) {
            ActivityEnum.NOT_ACTIVE.setValue(data.getTurnoverRateF());
            return ActivityEnum.NOT_ACTIVE;
        } else if(data.getTurnoverRateF().compareTo(ActivityEnum.NORMAL_ACTIVE.getStandard()) < 0) {
            ActivityEnum.LOW_ACTIVE.setValue(data.getTurnoverRateF());
            return ActivityEnum.LOW_ACTIVE;
        } else if(data.getTurnoverRateF().compareTo(ActivityEnum.VERY_ACTIVE.getStandard()) < 0) {
            ActivityEnum.NORMAL_ACTIVE.setValue(data.getTurnoverRateF());
            return ActivityEnum.NORMAL_ACTIVE;
        } else {
            ActivityEnum.VERY_ACTIVE.setValue(data.getTurnoverRateF());
            return ActivityEnum.VERY_ACTIVE;
        }
    }

}
