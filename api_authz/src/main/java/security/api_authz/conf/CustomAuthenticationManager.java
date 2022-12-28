package security.api_authz.conf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import jakarta.transaction.Transactional;
import security.api_authz.entity.User;
import security.api_authz.service.UserService;

public class CustomAuthenticationManager implements AuthenticationManager {
    @Autowired
    UserService userService;


    @Transactional
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.findByUsername(username);
        
        if (user==null) {
            throw new BadCredentialsException("Usuario o contraseña inválidos");
        }

        if (!userService.matchPassword(password,user.getPassword())){
            throw new BadCredentialsException("Usuario o contraseña inválidos");
        }
        user = userService.getUserWithRolesByUsername(username);

        return new UsernamePasswordAuthenticationToken(username, "", user.getAuthorities());
    }
}
