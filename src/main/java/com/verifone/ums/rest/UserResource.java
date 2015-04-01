package com.verifone.ums.rest;

import com.verifone.ums.entity.User;
import com.verifone.ums.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

/**
 * @author Pavel Mikhalchuk
 */
@RestController
@RequestMapping("ums/v1/user")
public class UserResource {

    @Autowired
    UserDao userDao;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<User> listUsers() {
        //return Lists.newArrayList(companyDao.findAll());
        Iterable<User> iterable = userDao.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(toList());

    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User createUser(@RequestBody User user) {
        //return Lists.newArrayList(companyDao.findAll());
        return userDao.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        //todo user.getId == null throw Exception
        //return Lists.newArrayList(companyDao.findAll());
        return userDao.save(user);
    }


}