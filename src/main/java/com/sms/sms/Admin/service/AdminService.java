package com.sms.sms.Admin.service;

import com.sms.sms.Admin.entity.Admin;
import com.sms.sms.db.repository.JpaRepository;
import com.sms.sms.db.repository.JpaRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class AdminService {
    private static final JpaRepository<Admin, UUID> repository = new JpaRepositoryImpl<>(Admin.class);

    public static void persistNewAdmin(Admin admin) {
        log.info("Admin inserted: {}", repository.save(admin));
    }

    public static List<Admin> findAllStudents() {
        List<Admin> admin = repository.findAll();
        log.info("Founded admin: {}", admin);
        return admin;
    }

    public static void removeAdminById(UUID student) {
        repository.deleteById(student);
        log.info("Admin removed: {}", student);
    }

    public static Admin updateAdmin(Admin admin) {
        Admin updated = repository.update(admin.getId(), admin);
        log.info("Admin updated: {}", admin);
        return updated;
    }

    public static Admin findAdminByUsername(String admin) {
        List<Admin> admins = repository.findByField("username", admin);
        if (admins.isEmpty()) {
            log.info("No admin found with username: {}", admin);
            return null;
        }
        log.info("Found student: {}", admins.getFirst());
        return admins.getFirst();
    }
}
