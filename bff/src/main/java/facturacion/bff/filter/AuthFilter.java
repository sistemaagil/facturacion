package facturacion.bff.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import facturacion.bff.authz.UserClient;
import feign.FeignException;

@Component
public class AuthFilter implements Filter {

    @Autowired UserClient userClient;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        ResponseEntity<String> httpResponse;
        String authHeader = httpRequest.getHeader("Authorization");
        String endpoint = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();
        if (endpoint.equals("/login/")){
            chain.doFilter(request, response);
        }else{
            try {
                httpResponse = userClient.hasAuthority(authHeader, method+":"+endpoint);
                if (httpResponse.getStatusCode() == HttpStatus.OK){
                    chain.doFilter(request, response);
                }
            }catch (FeignException exception){
                ((HttpServletResponse) response).setStatus(HttpStatus.FORBIDDEN.value());
            }
        }
    }
    
}
