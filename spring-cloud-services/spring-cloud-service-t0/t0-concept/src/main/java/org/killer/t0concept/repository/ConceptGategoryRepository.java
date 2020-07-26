package org.killer.t0concept.repository;

import org.killer.t0concept.entity.ConceptCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author killer
 * @date 2020/6/6 -  11:32
 **/
@Repository
public interface ConceptGategoryRepository<T extends ConceptCategory, ID> extends JpaRepository<T, ID> {

}
