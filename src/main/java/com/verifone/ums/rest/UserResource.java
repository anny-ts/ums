package com.verifone.ums.rest;

import com.verifone.ums.entity.User;
import com.verifone.ums.repositories.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "list", method = RequestMethod.GET, produces = JSON)
    public List<User> listUsers() {
        return userService.listAllUsers();
    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET, produces = JSON)
    public User findUserById(@PathVariable("user_id") String userId) {
        return userService.findUserById(userId);
    }

    @RequestMapping(params = {"email"}, method = RequestMethod.GET, produces = JSON)
    public User findUserByEmail(@RequestParam ("email") String email) {
        return userService.findUserByEmail(email);
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

    @RequestMapping(value = "/{user_id}", method = RequestMethod.DELETE, consumes = JSON)
    public void deleteUser(@PathVariable("user_id") String userId) {
        userService.deleteUser(Long.parseLong(userId));
    }


}