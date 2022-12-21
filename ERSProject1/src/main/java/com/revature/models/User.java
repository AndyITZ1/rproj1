package com.revature.models;

public class User {

    private int user_id;
    private String ers_username;
    private String ers_password;
    private String user_first_name;
    private String user_last_name;
    private int user_role_id_fk;
    private Role role;

    public User() {

    }

    public User(int user_id, String ers_username, String ers_password, String user_first_name, String user_last_name, Role role) {
        this.user_id = user_id;
        this.ers_username = ers_username;
        this.ers_password = ers_password;
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.role = role;
    }

    public User(String ers_username, String ers_password, String user_first_name, String user_last_name, Role role) {
        this.ers_username = ers_username;
        this.ers_password = ers_password;
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.role = role;
    }

    public User(String ers_username, String ers_password, String user_first_name, String user_last_name, int user_role_id_fk) {
        this.ers_username = ers_username;
        this.ers_password = ers_password;
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.user_role_id_fk = user_role_id_fk;
    }

    public User(String ers_username, String ers_password, int user_role_id_fk) {
        this.ers_username = ers_username;
        this.ers_password = ers_password;
        this.user_role_id_fk = user_role_id_fk;
    }

    public User(String ers_username, String ers_password) {
        // Constructor Chaining to emulate default parameter values
        this(ers_username, ers_password, 1);
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getErs_username() {
        return ers_username;
    }

    public void setErs_username(String ers_username) {
        this.ers_username = ers_username;
    }

    public String getErs_password() {
        return ers_password;
    }

    public void setErs_password(String ers_password) {
        this.ers_password = ers_password;
    }

    public String getUser_first_name() {
        return user_first_name;
    }

    public void setUser_first_name(String user_first_name) {
        this.user_first_name = user_first_name;
    }

    public String getUser_last_name() {
        return user_last_name;
    }

    public void setUser_last_name(String user_last_name) {
        this.user_last_name = user_last_name;
    }

    public int getUser_role_id_fk() {
        return user_role_id_fk;
    }

    public void setUser_role_id_fk(int user_role_id_fk) {
        this.user_role_id_fk = user_role_id_fk;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", ers_username='" + ers_username + '\'' +
                ", ers_password='" + ers_password + '\'' +
                ", user_first_name='" + user_first_name + '\'' +
                ", user_last_name='" + user_last_name + '\'' +
                ", user_role_id_fk=" + user_role_id_fk +
                ", role=" + role +
                '}';
    }
}
