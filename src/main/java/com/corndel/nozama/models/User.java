package com.corndel.nozama.models;

import com.corndel.nozama.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

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
        var query = "DELETE FROM users WHERE id = ?";
        try (var connection = DB.getConnection(); var statement = connection.prepareStatement(query);) {

            statement.setInt(1, id);
            var rs = statement.executeQuery();
            System.out.println(rs);
            return rs;
        }

    }

    public static User createUser(String username, String firstName, String lastName, String email, String avatar) throws SQLException {
        var query = "INSERT INTO users(username, firstName, lastName, email, avatar),VALUES(?,?,?,?,?)";

        try (var connection = DB.getConnection(); var statement = connection.prepareStatement(query);

        ) {
            statement.setString(1, username);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, email);
            statement.setString(5, avatar);
            var rs = statement.executeQuery();

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

    public static Boolean loginUser(String username, String password) throws SQLException {

        var query = String.format("SELECT username, password FROM users WHERE username = ?");
        try (var connection = DB.getConnection(); var statement = connection.prepareStatement(query);

        ) {
            statement.setString(1, username);
            var rs = statement.executeQuery();

            System.out.println("DB made connection");
            String dbUsername = rs.getString("username");

            String dbPassword = rs.getString("password");

            if (Objects.equals(username, dbUsername) && Objects.equals(password, dbPassword)) {
                System.out.println("User logged in successfully");
                return true;
            }
            System.out.println(String.format("Mismatch between username: %s, and %2s", username, dbUsername));

            System.out.println(String.format("Mismatch between pwd: %s, and %2s", password, dbPassword));

        }
        System.out.println("Sorry, we couldn't log you in");
        return false;
    }




}
