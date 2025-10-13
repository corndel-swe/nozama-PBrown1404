package com.corndel.nozama.models;

import com.corndel.nozama.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;

    public User(Integer id, String username, String firstName, String lastName, String email, String avatar) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public static ResultSet deleteUser(Integer id) throws SQLException {
        var query = "DELETE FROM users WHERE id = " + id;
        try (var connection = DB.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(query);
        ) {
            System.out.println(rs);
            return rs;
        }

    }

    public static User createUser(String username, String firstName, String lastName, String email, String avatar) throws SQLException {
        var query = "INSERT INTO users(username, firstName, lastName, email, avatar),VALUES(" + username + "," + firstName + "," + lastName + "," + email + "," + avatar + ");";
        try (var connection = DB.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(query);
        ) {
            System.out.println(rs);
            while (rs.next()) {
                var id = rs.getInt("id");
                var returnedUsername = rs.getString("username");
                var returnedFirstName = rs.getString("firstName");
                var returnedLastName = rs.getString("lastName");
                var returnedEmail = rs.getString("email");
                var returnedAvatar = rs.getString("avatar");

                var newUser = new User(id, returnedUsername, returnedFirstName, returnedLastName, returnedEmail, returnedAvatar);
                return newUser;
            }
        }
        System.out.println("Arthur may or may not of written broken code");
        return null;
    }
}
