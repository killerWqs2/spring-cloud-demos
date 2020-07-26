package org.killer.t0concept.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @author killer
 * @date 2020/06/06 - 12:17
 */
@Table(name = "concept_category_detail")
@Data
public class ConceptCategoryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer conceptId;

    private String tsCode;

    private LocalDate inDate;

    private LocalDate outDate;

    @Override
    public String toString() {
        return "ConceptCategoryDetail{" +
                "id=" + id +
                ", conceptId=" + conceptId +
                ", tsCode='" + tsCode + '\'' +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                '}';
    }

}
