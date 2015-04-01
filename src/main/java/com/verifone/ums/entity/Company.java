package com.verifone.ums.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Pavel Mikhalchuk
 */
@Entity
@Table(name = "company", uniqueConstraints = {
        @UniqueConstraint(columnNames = "company_name")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;

    @Column(name = "company_name")
    private String name;

//    @ManyToMany
//    @JoinTable(name = "company_user", joinColumns = @JoinColumn(name = "company_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private Set<User> users;
//
    public Company() {
    }

    public Company(long id) {
        this.companyId = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//
//    public void addUser(User user) {
//        if (this.users != null) {
//            this.users.add(user);
//        } else {
//            this.users = new HashSet<>();
//            this.users.add(user);
//        }
//
//    }
}