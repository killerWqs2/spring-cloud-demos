package org.killer.t0concept.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author killer
 * @date 2020/06/06 - 11:24
 */
@Data
public class ConceptCategoryDTO {

    private List<List<String>> items;

    private List<String> fields;

    public List<ConceptCategory> transform() {
        ArrayList<ConceptCategory> conceptGategoryResponses = new ArrayList<>();

        for (List<String> item : items) {
            ConceptCategory shareData = new ConceptCategory();
            int i = 0;
            for (String field : fields) {
                switch (field) {
                    case "code":
                        shareData.setCode(item.get(i++));
                        break;
                    case "name":
                        shareData.setName(item.get(i++));
                        break;
                    case "src":
                        shareData.setSrc(item.get(i++));
                        break;
                    default:
                        // do nothing
                }
            }
        }

        return conceptGategoryResponses;
    }

}
