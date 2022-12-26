package security.api_authz.conf;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
    	setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }



	@Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {


            if (req.getHeader("Authorization")==null || req.getHeader("Authorization").isEmpty())
                throw new AuthenticationCredentialsNotFoundException("Credentials not found");
            String header=req.getHeader("Authorization").replace("Basic ","");

            byte[] decodedBytes = Base64.getDecoder().decode(header);
            header = new String(decodedBytes);

            String[] credenciales = header.split(":");
            if (credenciales.length <= 1)
                throw new AuthenticationCredentialsNotFoundException("Credentials not found");
		
	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(credenciales[0], credenciales[1], new ArrayList<>());
	        
	        Authentication auth = authenticationManager.authenticate(authToken);
	        return auth;
		}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req,
											HttpServletResponse res,
											FilterChain chain,
											Authentication auth) throws IOException, ServletException{

		String username = auth.getPrincipal().toString();
		Collection<? extends GrantedAuthority> authorities=auth.getAuthorities();
		String role = authorities
            .stream()
            .map(authName -> authName.getAuthority()+";")
            .reduce("", String::concat);
        role = role.substring(0, role.length()-1);
        String token = jwtUtil.generateToken(username, role);
        res.addHeader("Authorization", "Bearer " + token);
        res.addHeader("Access-Control-Expose-Headers", "Authorization");
	}

    @Override
    public AuthenticationManager getAuthenticationManager(){
        return this.authenticationManager;
    }
	
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		 
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().append(json(exception.getLocalizedMessage()));

        }
        
        private String json(String message) {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Not authorized\", "
                + "\"message\": "+ message+ ", "
                + "\"path\": \"/login\"}";
        }
	}


}

