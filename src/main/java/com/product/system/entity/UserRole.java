package com.product.system.entity;

import javax.persistence.*;

/**
 * Created by Sonikb on 21.08.2017.
 */
@Entity
@Table(name = "roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ROLE_TYPE", length = 15, nullable = false, unique = true)
    private String roleType = UserRoleType.USER.getUserRoleType();

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", roleType='" + roleType + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
