package in.manager.system.controller;

import in.manager.system.exception.InactiveUserException;
import in.manager.system.exception.InvalidUserCredentialsException;
import in.manager.system.model.CustomUserDetails;
import in.manager.system.model.JwtRequest;
import in.manager.system.model.Manager;
import in.manager.system.service.JwtUserDetailsService;
import in.manager.system.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        Manager manager = null;

        if (userDetails instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
            manager = customUserDetails.getManager();
            manager.setToken(token);
        }

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("manager", manager);

        return ResponseEntity.ok(dataMap);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new InactiveUserException("Inactive user name: " + username);
        } catch (BadCredentialsException e) {
            throw new InvalidUserCredentialsException("Please check username or password");
        }
    }
}