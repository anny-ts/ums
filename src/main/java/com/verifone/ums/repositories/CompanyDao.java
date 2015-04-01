package com.verifone.ums.repositories;

import com.verifone.ums.entity.Company;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by anna on 3/31/15.
 */
@Repository
public interface CompanyDao extends PagingAndSortingRepository<Company, Long>{
}
