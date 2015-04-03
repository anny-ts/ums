package com.verifone.ums.repositories;

import com.verifone.ums.entity.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * Created by anna tsiunchyk on 3/31/15.
 */
public interface CompanyDao extends PagingAndSortingRepository<Company, Long>{

//    @Query
//    List<Company> findByUserId(long userId);
}
