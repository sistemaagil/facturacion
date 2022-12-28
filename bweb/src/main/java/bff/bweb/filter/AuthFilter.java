package bff.bweb.filter;

import java.io.IOException;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import bff.bweb.authz.UserClient;
import feign.FeignException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AuthFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(AuthFilter.class);

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
                logger.error(exception.getLocalizedMessage());
                ((HttpServletResponse) response).setStatus(HttpStatus.FORBIDDEN.value());
            }
        }
    }
    
}
