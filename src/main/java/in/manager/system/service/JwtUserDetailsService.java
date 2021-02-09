package in.manager.system.service;

import in.manager.system.dto.ManagerVo;
import in.manager.system.model.CustomUserDetails;
import in.manager.system.model.Manager;
import in.manager.system.repository.ManagerRepository;
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


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    ManagerRepository managerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		ManagerVo foundUsersDetail = managerRepository.findByEmail(email.trim());
        Manager managerDetail = null;
        if (foundUsersDetail != null) {
            managerDetail = new Manager();

            managerDetail.setId(foundUsersDetail.getId());
            managerDetail.setEmail(foundUsersDetail.getEmail());
            managerDetail.setPassword(foundUsersDetail.getPassword());
            managerDetail.setUserRole(foundUsersDetail.getUserRole());

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(managerDetail.getUserRole()));

            User user = new User(managerDetail.getEmail(), managerDetail.getPassword(), grantedAuthorities);

            return new CustomUserDetails(user, managerDetail);

        } else {
            throw new UsernameNotFoundException("Manager not found with : " + email);
        }

    }

}