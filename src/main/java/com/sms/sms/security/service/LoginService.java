package com.sms.sms.security.service;

public interface LoginService {
    boolean[] validateCredentials(String username, String password);
    String getStoredHashedPasswordForUser(String username);
}
