package com.sms.sms.security.service;

import com.sms.sms.Admin.entity.Admin;
import com.sms.sms.Admin.service.AdminService;
import com.sms.sms.User.entity.Student;
import com.sms.sms.db.service.StudentService;
import com.sms.sms.security.pass.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class LoginServiceImpl implements LoginService {
    public static final ConcurrentHashMap<String, UUID> loggedInUsers = new ConcurrentHashMap<>();

    @Override
    public boolean[] validateCredentials(String username, String password) {
        return username.startsWith("P") ?
                new boolean[]{true, BCrypt.checkpw(password, getStoredHashedPasswordForAdmin(username))} :
                new boolean[]{false, BCrypt.checkpw(password, getStoredHashedPasswordForUser(username))};
    }

    @Override
    public String getStoredHashedPasswordForUser(String username) {
        Student student = StudentService.findStudentByUsername(username);
        assert student != null;
        loggedInUsers.put(student.getUsername(), student.getId());
        return student.getPassword();
    }

    @Override
    public String getStoredHashedPasswordForAdmin(String username) {
        insertAdmins();
        Admin admin = AdminService.findAdminByUsername(username);
        assert admin != null;
        loggedInUsers.put(admin.getUsername(), admin.getId());
        return admin.getPassword();
    }

    private void insertAdmins() {
        AdminService.persistNewAdmin(Admin
                .builder()
                .fullName("Abdulaxad Isroilov")
                .username("P2310110")
                .password(String.valueOf(PasswordEncoder.encodePassword("1234qwer")))
                .build()
        );
        AdminService.persistNewAdmin(Admin
                .builder()
                .fullName("Bobur")
                .username("P2310114")
                .password(String.valueOf(PasswordEncoder.encodePassword("1234qwer")))
                .build()
        );
        AdminService.persistNewAdmin(Admin
                .builder()
                .fullName("MuhammadIso")
                .username("P2310122")
                .password(String.valueOf(PasswordEncoder.encodePassword("1234qwer")))
                .build()
        );
        AdminService.persistNewAdmin(Admin
                .builder()
                .fullName("Kamron")
                .username("P2310115")
                .password(String.valueOf(PasswordEncoder.encodePassword("1234qwer")))
                .build()
        );
    }

}
