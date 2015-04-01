package com.verifone.ums.repositories;

import com.verifone.ums.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by anna on 3/31/15.
 */
public interface UserDao extends PagingAndSortingRepository<User, Long> {
}
