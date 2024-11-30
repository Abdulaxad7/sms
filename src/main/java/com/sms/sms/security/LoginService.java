package com.sms.sms.security;

import org.mindrot.jbcrypt.BCrypt;

public class LoginService {

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean validateCredentials(String username, String password) {
        String storedHashedPassword = getStoredHashedPasswordForUser(username);
        System.out.println(username+"   "+password);
        System.out.println(BCrypt.checkpw(password, storedHashedPassword));
        return BCrypt.checkpw(password, storedHashedPassword);
    }

    // This simulates fetching a hashed password from the database
    private String getStoredHashedPasswordForUser(String username) {
        // In a real application, you'd fetch this from a database.
        // Here, we simulate a hardcoded hashed password for "validPassword"
        if ("validUsername".equals(username)) {
            return "$2a$10$mHY/GdzctN./3en.JFZoeOuFLNatvIhDwq.1uWdndIimon0wFFmsu";  // Example hashed password
        }
        return null;
    }
}
