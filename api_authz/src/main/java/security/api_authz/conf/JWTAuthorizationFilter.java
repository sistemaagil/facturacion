package security.api_authz.conf;

import java.io.IOException;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import security.api_authz.entity.User;
import security.api_authz.service.UserService;



public class JWTAuthorizationFilter  extends BasicAuthenticationFilter {

    UserService userService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        
        this.userService = userService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header=request.getHeader(JWTUtil.TOKEN_HEADER);
        // Filtra aquellos sin token (tal vez acceso sin autorización)
        if(header==null||!header.startsWith(JWTUtil.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            authentication = getAuthentication(header);
        }catch (RuntimeException ex ) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(new ObjectMapper().writeValueAsString("No access, "+ex.getMessage()));
            return;
        }
        
        // Establece la información de autenticación del usuario y jwtToken genera UsernamePasswordAuthenticationToken
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        String token = header.replace(JWTUtil.TOKEN_PREFIX, "");
        
        String username = JWTUtil.getUsername(token);
        
        User usuario = userService.getUserWithAuthoritiesByUsername(username);
        
        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null,usuario.getAuthorities()
            );
        }
        return null;
    }
}
