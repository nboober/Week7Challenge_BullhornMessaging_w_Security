package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception{
        roleRepository.save(new Role("USER"));

        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true, "Jim", "https://res.cloudinary.com/dmfaehjot/image/upload/v1564415427/Week7Challenge/0_Xz-_cHSO6txphvHt_qnhfyx.png");

        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com", "password", "Admin", "User", true, "admin", "https://res.cloudinary.com/dmfaehjot/image/upload/v1564415442/Week7Challenge/28009966-grunge-rubber-stamp-with-text-admin-vector-illustration_zqrc4g.jpg");
        user.setRoles(Arrays.asList(adminRole, userRole));
        userRepository.save(user);
    }

}
