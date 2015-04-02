package com.verifone.ums.rest;

import com.verifone.ums.entity.User;
import com.verifone.ums.repositories.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.verifone.ums.rest.HttpConstants.JSON;

/**
 * @author Pavel Mikhalchuk
 */
@RestController
@RequestMapping("ums/v1/user")
public class UserResource {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = JSON)
    public List<User> listUsers() {
        return userService.listAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = JSON, produces = JSON)
    @ResponseBody
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = JSON, produces = JSON)
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        //todo user.getId == null throw Exception
        //return Lists.newArrayList(companyDao.findAll());
        return userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, consumes = JSON)
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }


}