package com.apartment.apart.admin;

import com.apartment.apart.user.SiteUser;
import com.apartment.apart.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public Admin adminCreate(String adminname, String password, Boolean adminCheck) {
        Admin admin = new Admin();
        admin.setAdminName(adminname);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setAdmincheck(true);
        this.adminRepository.save(admin);
        return admin;
    }
}
