package com.msc.authentication;

import com.msc.dao.DAO;
import com.msc.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Paul on 12/08/2017.
 */
// Ideas for page class from http://www.mkyong.com/spring-security/spring-security-hibernate-annotation-example/
// This Class deals with user Authentication on login
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    // Instance of DAO
    private DAO userDao;

    // This Method Finds the User by username and links a list of granted authorities to the user
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        com.msc.model.User user = userDao.findByUserName(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
        return buildUserForAuthentication(user, authorities);
    }

    // This Method Converts the instance of the User Class in the model package to a UserDetails.User object
    private User buildUserForAuthentication(com.msc.model.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    // This Method sets the granted authorities based on what role the user has
    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
        return Result;
    }

    public DAO getUserDao() {
        return userDao;
    }

    public void setUserDao(DAO userDao) {
        this.userDao = userDao;
    }
}


