package org.killer.t0sharedata.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author killer
 * @date 2020/05/28 - 17:02
 */
@Data
public class ShareDataResponse {

    private List<List<String>> items;

    private List<String> fields;

    /** 这种方式真的没办法 */
    public List<ShareData> transform() {

        ArrayList<ShareData> shareDataResponses = new ArrayList<>();

        for (List<String> item : items) {
            ShareData shareData = new ShareData();
            int i = 0;
            for (String field : fields) {

                switch (field) {
                    case "ts_ode" :
                        shareData.setTsCode(item.get(i++));
                        break;
                    case "trade_date" :
                        shareData.setTradeDate(item.get(i++));
                        break;
                    case "open" :
                        shareData.setOpen(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "close" :
                        shareData.setClose(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "high" :
                        shareData.setHigh(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "low" :
                        shareData.setLow(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "pre_close" :
                        shareData.setPreClose(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "change" :
                        shareData.setChange(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "pct_chg" :
                        shareData.setPctChg(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "vol" :
                        shareData.setVol(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    case "amount" :
                        shareData.setAmount(BigDecimal.valueOf(Float.parseFloat(item.get(i++))));
                        break;
                    default:
                }

            }

            shareDataResponses.add(shareData);
        }

        return shareDataResponses;

    }

}
