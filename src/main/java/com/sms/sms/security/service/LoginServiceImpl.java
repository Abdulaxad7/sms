package com.sms.sms.security.service;

import org.mindrot.jbcrypt.BCrypt;

public class LoginServiceImpl implements LoginService{

    public boolean[] validateCredentials(String username, String password) {
        String storedHashedPassword = getStoredHashedPasswordForUser(username);
        System.out.println(username + "   " + password);
        System.out.println(BCrypt.checkpw(password, storedHashedPassword));

        return username.equals("validAdmin")?
                new boolean[]{true, BCrypt.checkpw(password, storedHashedPassword)}:
                new boolean[]{false, BCrypt.checkpw(password, storedHashedPassword)};
    }

    // This simulates fetching a hashed password from the database
    public String getStoredHashedPasswordForUser(String username) {
        if ("validUsername".equals(username)) {
            return "$2a$10$mHY/GdzctN./3en.JFZoeOuFLNatvIhDwq.1uWdndIimon0wFFmsu";  // Example hashed password
        } else if ("validAdmin".equals(username)) {
            return "$2a$10$mHY/GdzctN./3en.JFZoeOuFLNatvIhDwq.1uWdndIimon0wFFmsu";
        }
        return null;
    }
}
