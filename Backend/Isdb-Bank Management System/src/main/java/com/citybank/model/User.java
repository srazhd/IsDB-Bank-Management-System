package com.citybank.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
public class User extends BaseModel{
    private String name;
    private String email;
    @NaturalId
    private String username;
    private String password;
    
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Address address;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Home home;
    
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role" , joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private List<Role> roles;
}
