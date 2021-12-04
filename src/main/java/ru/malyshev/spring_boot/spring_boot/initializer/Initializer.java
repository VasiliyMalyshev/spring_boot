package ru.malyshev.spring_boot.spring_boot.initializer;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.malyshev.spring_boot.spring_boot.dao.RoleRepository;
import ru.malyshev.spring_boot.spring_boot.dao.UserRepository;
import ru.malyshev.spring_boot.spring_boot.model.Role;
import ru.malyshev.spring_boot.spring_boot.model.User;
import ru.malyshev.spring_boot.spring_boot.service.RoleService;
import ru.malyshev.spring_boot.spring_boot.service.RoleServiceImpl;
import ru.malyshev.spring_boot.spring_boot.service.UserService;
import ru.malyshev.spring_boot.spring_boot.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Set;

@Component
public class Initializer {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private void datainit() throws SQLException {
        Role role = new Role(1L, "ROLE_ADMIN");
        Role role2 = new Role(2L,"ROLE_USER");
        roleService.addRole(role);
        roleService.addRole(role2);

        Set<Role> roleSet1 = Collections.singleton(roleService.getRoleByName("ROLE_ADMIN"));
        Set<Role> roleSet2 = Collections.singleton(roleService.getRoleByName("ROLE_USER"));

        User user = new User();
        user.setUsername("Vasya");
        user.setRoles(roleSet1);
        user.setPassword("100");
        userService.addUser(user);

        User user2 = new User();
        user2.setUsername("Dude");
        user2.setRoles(roleSet2);
        user2.setPassword("100");
        userService.addUser(user2);

    }

    @PostConstruct
    private void init() throws SQLException {
        datainit();
    }
}