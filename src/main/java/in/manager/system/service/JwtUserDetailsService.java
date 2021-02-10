package in.manager.system.service;

import in.manager.system.model.CustomUserDetails;
import in.manager.system.model.Manager;
import in.manager.system.repository.ManagerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUserDetailsService.class);
    @Autowired
    ManagerRepository managerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.info("email Id is --> {}", email);
        Optional<Manager> foundUsersDetail = managerRepository.findManagerByEmail(email.trim());
        Manager managerDetail = null;
        if (foundUsersDetail.isPresent()) {
            managerDetail = new Manager();

            managerDetail.setId(foundUsersDetail.get().getId());
            managerDetail.setEmail(foundUsersDetail.get().getEmail());
            managerDetail.setPassword(foundUsersDetail.get().getPassword());
            managerDetail.setUserRole(foundUsersDetail.get().getUserRole());

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(managerDetail.getUserRole()));

            User user = new User(managerDetail.getEmail(), managerDetail.getPassword(), grantedAuthorities);

            return new CustomUserDetails(user, managerDetail);

        } else {
            throw new UsernameNotFoundException("Manager not found with : " + email);
        }

    }

}