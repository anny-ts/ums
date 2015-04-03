package com.verifone.ums.repositories;

import com.verifone.ums.entity.Company;
import com.verifone.ums.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

/**
 * Created by anna tsiunchyk on 3/31/15.
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

    public Company findCompanyById(long companyId) {
        return companyDao.findOne(companyId);
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
        List<User> users = userDao.findUsersByCompanyId(company.getCompanyId());

        for (User user : users) {
            user.getCompanies().remove(company);
            userDao.save(user);
        }

        userDao.findUsersByCompanyId(company.getCompanyId()).stream()
                .forEach(u -> {
                    u.getCompanies().remove(company);
                    userDao.save(u);
                });
//        for (User user : users) {
//            user.getCompanies().remove(company);
//            userDao.save(user);
//        }
        companyDao.delete(company);
    }

    @Transactional(readOnly = false)
    public List<User> getUsersForCompany(long companyId) {
        return userDao.findUsersByCompanyId(companyId);
    }

    @Transactional(readOnly = false)
    public User removeUserFromCompany(long companyId, long userId) {
        User user = userDao.findOne(userId);
        user.getCompanies().stream().filter(c -> c.getCompanyId().equals(companyId))
                .forEach(c -> {
                    user.getCompanies().remove(c);
                    userDao.save(user);
                });
        return userDao.findOne(userId);
    }
}
