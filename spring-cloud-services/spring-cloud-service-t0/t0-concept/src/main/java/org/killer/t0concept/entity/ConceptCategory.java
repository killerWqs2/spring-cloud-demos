package org.killer.t0concept.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author killer
 * @date 2020/06/06 - 11:23
 */
@Data
public class ConceptCategory {

    @Id
    private String code;

    private String name;

    private String src;

}
