package com.verifone.ums.repositories;

import com.verifone.ums.entity.Company;
import com.verifone.ums.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

/**
 * Created by anna on 3/31/15.
 */
@Component
@Transactional
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = false)
    public User addUserToCompany(String companyId, String userId) {
        User user = userDao.findOne(Long.parseLong(userId));
        Company company = companyDao.findOne(Long.parseLong(companyId));

        user.addCompany(company);
        return userDao.save(user);
    }

    public List<Company> listAllCompanies() {
        //return Lists.newArrayList(companyDao.findAll());
        Iterable<Company> iterable = companyDao.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(toList());
    }

    @Transactional(readOnly = false)
    public Company createCompany(Company company) {
        return companyDao.save(company);
    }

    @Transactional(readOnly = false)
    public Company updateCompany(Company company) {
        return companyDao.save(company);
    }

    @Transactional(readOnly = false)
    public void deleteCompany(Company company) {
        companyDao.delete(company);
    }





}
