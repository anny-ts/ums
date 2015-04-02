package com.verifone.ums.rest;

import com.verifone.ums.entity.Company;
import com.verifone.ums.entity.User;
import com.verifone.ums.repositories.CompanyDao;
import com.verifone.ums.repositories.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

import static com.verifone.ums.rest.HttpConstants.JSON;
import static java.util.stream.Collectors.toList;

/**
 * @author Pavel Mikhalchuk
 */
@RestController
@RequestMapping("ums/v1/company")
public class CompanyResource {


    @Qualifier("companyService")
    @Autowired
    private CompanyService companyService;

    @RequestMapping(method = RequestMethod.GET, produces = JSON)
    public List<Company> listCompanies() {
        return companyService.listAllCompanies();

    }

    @RequestMapping(method = RequestMethod.POST, consumes = JSON, produces = JSON)
    @ResponseBody
    public Company createCompany(@RequestBody Company company) {
         return companyService.createCompany(company);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = JSON, produces = JSON)
    @ResponseBody
    public Company editCompany(@RequestBody Company company) {
        return companyService.updateCompany(company);
    }

    @RequestMapping(method = RequestMethod.DELETE, consumes = JSON)
    public void deleteCompany(@RequestBody Company company) {
        companyService.deleteCompany(company);
    }

    @RequestMapping(value = "/{company_id}/user/{user_id}", method = RequestMethod.POST, consumes = JSON, produces = JSON)
    @ResponseBody
    public User addUserToCompany(@PathVariable("company_id") String company_id, @PathVariable("user_id") String user_id) {
        //return Lists.newArrayList(companyDao.findAll());
        return companyService.addUserToCompany(company_id, user_id);
    }


}