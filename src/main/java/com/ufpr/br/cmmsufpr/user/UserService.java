package com.ufpr.br.cmmsufpr.user;

import com.ufpr.br.cmmsufpr.user.model.PermissionModel;
import com.ufpr.br.cmmsufpr.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user;
        try {
            user = UserRepository.getUserByEmailOrNull(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (PermissionModel permission : user.getPermissions()){
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

    public void saveUser(UserModel userModel) {


    }

}
