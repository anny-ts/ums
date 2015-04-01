package com.verifone.ums.rest;

import com.verifone.ums.entity.Company;
import com.verifone.ums.entity.User;
import com.verifone.ums.repositories.CompanyDao;
import com.verifone.ums.repositories.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("ums/v1/company")
public class CompanyResource {


    @Autowired
    private CompanyDao companyDao;

    @Autowired
    CompanyService companyService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Company> listCompanies() {
        //return Lists.newArrayList(companyDao.findAll());
        Iterable<Company> iterable = companyDao.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(toList());

    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Company createCompany(@RequestBody Company company) {
        //return Lists.newArrayList(companyDao.findAll());
         return companyDao.save(company);
    }

    @RequestMapping(value = "/{company_id}/user/{user_id}", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User addUserToCompany(@PathVariable("company_id") String company_id, @PathVariable("user_id") String user_id) {
        //return Lists.newArrayList(companyDao.findAll());
        return companyService.addUserToCompany(company_id, user_id);
    }


}