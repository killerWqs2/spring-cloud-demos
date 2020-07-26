package org.killer.t0datafetch.repository;

import org.killer.t0listedcompany.entity.listedCompany.ListedCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author killer
 * @date 2020/05/2 - 10:49
 */
@Repository
public interface ListedCompanyRepository<T extends ListedCompany, ID extends Serializable> extends JpaRepository<T, ID> {

    // @Modifying
    // @Query(value = "", nativeQuery = true)
    // void updateById(@Param("listedCompany") ListedCompany listedCompany);

    /** 获取今日为获取数据的数据400 */
    @Query(value = "SELECT ts_code FROM t0_listed_company WHERE fetched_tody = 0  LIMIT 400", nativeQuery = true)
    List<ListedCompany> getNotFetchedDataCompany();

}
