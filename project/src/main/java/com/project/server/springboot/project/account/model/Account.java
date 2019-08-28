package com.project.server.springboot.project.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.server.springboot.project.account.util.StringUtil;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fullName;
    private String username;
//    private String password;
//    private String salt;
    private String phone;
    private String email;
    private String address;
    private int status;
//    private int role;

    public Account() {
        this.status = Status.ACTIVE.getValue();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void hashPassword(String password) {
//        if (this.salt == null || this.salt.length() == 0) {
//            this.salt = StringUtil.generateSalt();
//        }
//        this.password = StringUtil.hashPassword(password, this.salt);
//    }
//
//    public String getSalt() {
//        return salt;
//    }
//
//    public void setSalt(String salt) {
//        this.salt = salt;
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status.getValue();
    }

//    public int getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role.getValue();
//    }

    private enum Status {
        ACTIVE(1), DEACTIVE(0), DELETED(-1);

        private int value;

        Status(int value) {
            this.value = value;
        }

        public static Status findByValue(int value) {
            for (Status s : Status.values()) {
                if (s.value == value) {
                    return s;
                }
            }
            return Status.DEACTIVE;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

//    private enum Role {
//        USER(1), ADMIN(0), STUDENT(-1);
//
//        private int value;
//
//        Role(int value) {
//            this.value = value;
//        }
//
//        public static Role findByValue(int value) {
//            for (Role r : Role.values()) {
//                if (r.value == value) {
//                    return r;
//                }
//            }
//            return Role.USER;
//        }
//
//        public int getValue() {
//            return value;
//        }
//
//        public void setValue(int value) {
//            this.value = value;
//        }
//    }
}
