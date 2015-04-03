package com.verifone.ums.repositories;

import com.verifone.ums.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

/**
 * Created by anna tsiunchyk on 4/1/15.
 */
@Component
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> listAllUsers() {
        //return Lists.newArrayList(companyDao.findAll());
        Iterable<User> iterable = userDao.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(toList());
    }

    public User findUserByEmail(String email) {
        return userDao.findByLoginId(email);
    }

    public User findUserById(String userId) {
        return userDao.findOne(Long.parseLong(userId));
    }

    @Transactional(readOnly = false)
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Transactional(readOnly = false)
    public User updateUser(User user) {
        User original = userDao.findOne(user.getUserId());
        user.setCompanies(original.getCompanies());
        return userDao.save(user);
    }

    @Transactional(readOnly = false)
    public void deleteUser(long userId) {
        userDao.delete(userId);
    }

    @Transactional(readOnly = true)
    public List<User> findUsersByCompanyId(String companyId) {
        return userDao.findUsersByCompanyId(Long.parseLong(companyId));
    }

}
