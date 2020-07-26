package org.killer.t0concept.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author killer
 * @date 2020/06/06 - 12:33
 */
@Data
public class ConceptCategoryDetailDTO {

    private List<List<String>> items;

    private List<String> fields;

    public List<ConceptCategoryDetail> transform() {
        ArrayList<ConceptCategoryDetail> conceptGategoryDetailResponses = new ArrayList<>();

        DateTimeFormatter yyyymMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

        for (List<String> item : items) {
            ConceptCategoryDetail conceptCategoryDetail = new ConceptCategoryDetail();
            int i = 0;
            for (String field : fields) {
                switch (field) {
                    case "id":
                        conceptCategoryDetail.setConceptId(Integer.parseInt(item.get(i++)));
                        break;
                    case "ts_code":
                        conceptCategoryDetail.setTsCode(item.get(i++));
                        break;
                    case "in_date":
                        conceptCategoryDetail.setInDate(LocalDate.parse(item.get(i++), yyyymMdd));
                        break;
                    case "out_date":
                        conceptCategoryDetail.setOutDate(LocalDate.parse(item.get(i++), yyyymMdd));
                        break;
                    default:
                }
            }
        }

        return conceptGategoryDetailResponses;
    }
}
