package com.verifone.ums.repositories;

import com.verifone.ums.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

/**
 * Created by anna on 4/1/15.
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

    @Transactional(readOnly = false)
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Transactional(readOnly = false)
    public User updateUser(User user) {
        return userDao.save(user);
    }

    @Transactional(readOnly = false)
    public void deleteUser(User user) {
        userDao.delete(user);
    }

}
