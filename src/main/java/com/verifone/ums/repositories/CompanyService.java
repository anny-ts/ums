package com.verifone.ums.repositories;

import com.verifone.ums.entity.Company;
import com.verifone.ums.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by anna on 3/31/15.
 */
@Configuration
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private UserDao userDao;

    public User addUserToCompany(String companyId, String userId) {
        User user = userDao.findOne(Long.parseLong(userId));
        Company company = companyDao.findOne(Long.parseLong(companyId));

        user.addCompany(company);
        return userDao.save(user);
    }

}
