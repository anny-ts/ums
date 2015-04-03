package com.verifone.ums.repositories;

import com.verifone.ums.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by anna tsiunchyk on 3/31/15.
 */
public interface UserDao extends PagingAndSortingRepository<User, Long> {

    @Query("SELECT u FROM User u join u.companies c WHERE c.companyId = ?#{[0]}")
    List<User> findUsersByCompanyId(Long id);

    User findByLoginId(String email);

}
