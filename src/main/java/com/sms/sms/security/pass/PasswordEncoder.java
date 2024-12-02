package com.sms.sms.security.pass;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {
    public static char[] encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10)).toCharArray();
    }
}
