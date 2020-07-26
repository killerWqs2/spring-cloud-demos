package org.killer.t0concept.repository;

import org.killer.t0concept.entity.ConceptCategoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author killer
 * @date 2020/06/06 - 12:49
 */
public interface ConceptCategoryDetailRepository<T extends ConceptCategoryDetail, ID> extends JpaRepository<T, ID> {

}
