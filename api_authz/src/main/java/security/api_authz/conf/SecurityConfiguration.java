package security.api_authz.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import security.api_authz.service.UserService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration{

	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private UserService userService;
	
	@Bean                                                     
	public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {

		http
		.csrf().disable()
		.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/login").permitAll()
				.anyRequest().authenticated()
		);
		http.addFilterBefore(new JWTAuthenticationFilter(authenticationManager(), jwtUtil), BasicAuthenticationFilter.class);
		http.addFilterAfter(new JWTAuthorizationFilter(authenticationManager(), userService),BasicAuthenticationFilter.class);
		return http.build();

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		// Aquí puedes configurar tu AuthenticationManager personalizado
		return new CustomAuthenticationManager();
	}

}
