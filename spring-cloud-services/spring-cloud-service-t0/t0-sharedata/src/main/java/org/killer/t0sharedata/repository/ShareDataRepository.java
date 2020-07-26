package org.killer.t0sharedata.repository;

import org.killer.t0sharedata.entity.ShareData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author killer
 * @date 2020/05/28 - 16:58
 */
@Repository
public interface ShareDataRepository<T extends ShareData, ID extends Serializable> extends JpaRepository<T, ID> {

}
