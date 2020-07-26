package org.killer.t0sharedata.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author killer
 * @date 2020/06/02 - 22:41
 */
@Data
public class ShareDataIndexResponse {

    private List<List<String>> items;

    private List<String> fields;

    public List<ShareDataIndex> transform() {

        ArrayList<ShareDataIndex> shareDataIndexResponses = new ArrayList<>();

        for (List<String> item : items) {
            ShareDataIndex shareData = new ShareDataIndex();
            int i = 0;
            for (String field : fields) {

                switch (field) {
                    case "ts_code":
                        shareData.setTsCode(item.get(i++));
                        break;
                    case "turnover_rate":
                        shareData.setTurnoverRate(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "turnover_rate_f":
                        shareData.setTurnoverRateF(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "volume_ratio":
                        shareData.setVolumeRatio(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "pe_ttm":
                        shareData.setPeTTM(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "total_share":
                        shareData.setTotalShare(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "float_share":
                        shareData.setFloatShare(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "free_share":
                        shareData.setFreeShare(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "total_mv":
                        shareData.setTotalMv(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "circ_mv":
                        shareData.setCircMv(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    default:
                }

            }

            shareDataIndexResponses.add(shareData);

        }

        return shareDataIndexResponses;
    }

}
