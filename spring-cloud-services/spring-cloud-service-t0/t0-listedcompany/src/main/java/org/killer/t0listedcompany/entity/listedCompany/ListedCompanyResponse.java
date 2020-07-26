package org.killer.t0listedcompany.entity.listedCompany;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author killer
 * @date 2020/05/28 - 11:40
 */
@Data
public class ListedCompanyResponse {

    private List<List<String>> items;

    private List<String> fields;

    public List<ListedCompany> transform() {
        // 怎么写
        DateTimeFormatter yyyymMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

        ArrayList<ListedCompany> listedCompanies = new ArrayList<>(items.size());

        for (List<String> item : items) {
            ListedCompany listedCompany = new ListedCompany();
            int i = 0;
            for (String field : fields) {
                switch (field) {
                    case "ts_code":
                        listedCompany.setTsCode(item.get(i++));
                        break;
                    case "symbol":
                        listedCompany.setSymbol(item.get(i++));
                        break;
                    case "name":
                        listedCompany.setName(item.get(i++));
                        break;
                    case "area":
                        listedCompany.setArea(item.get(i++));
                        break;
                    case "industry":
                        listedCompany.setIndustry(item.get(i++));
                        break;
                    case "fullname":
                        listedCompany.setFullName(item.get(i++));
                        break;
                    case "enname":
                        listedCompany.setEnName(item.get(i++));
                        break;
                    case "market":
                        listedCompany.setMarket(item.get(i++));
                        break;
                    case "exchange":
                        listedCompany.setExchange(item.get(i++));
                        break;
                    case "curr_type":
                        listedCompany.setCurrType(item.get(i++));
                        break;
                    case "list_status":
                        listedCompany.setListStatus(item.get(i++));
                        break;
                    case "list_date":
                        LocalDate listDate = LocalDate.parse(item.get(i++), yyyymMdd);
                        listedCompany.setListDate(listDate);
                        break;
                    case "delist_date":
                        String str = item.get(i++);
                        // 不一定退市
                        if(StringUtils.hasText(str)) {
                            LocalDate delistDate = LocalDate.parse(str, yyyymMdd);
                            listedCompany.setDelistDate(delistDate);
                        }

                        break;
                    case "is_hs":
                        listedCompany.setIsHs(item.get(i++));
                    default:
                }
            }

            listedCompanies.add(listedCompany);
        }

        return listedCompanies;
    }

    public static void main(String[] args) {
        DateTimeFormatter yyyymMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

        System.out.println(LocalDate.parse("19910411", yyyymMdd));
    }
}