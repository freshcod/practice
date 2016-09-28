package com.coral.practice.web.domain;


import java.util.Date;


public class AuthUser {
    private int id;

    private String password;

    private Date lastLogin;

    private int isSuperuser;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private int isStaff;

    private int isActive;

    private Date dateJoined;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getIsSuperuser() {
        return isSuperuser;
    }

    public void setIsSuperuser(int isSuperuser) {
        this.isSuperuser = isSuperuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(int isStaff) {
        this.isStaff = isStaff;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "id=" + id +
                ", password=" + password +
                ", lastLogin=" + lastLogin +
                ", isSuperuser=" + isSuperuser +
                ", username=" + username +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email +
                ", isStaff=" + isStaff +
                ", isActive=" + isActive +
                ", dateJoined=" + dateJoined +
                '}';
    }
}