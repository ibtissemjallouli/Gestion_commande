package tn.uma.isamm.spring.tp1.sec;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties.ConfigureAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import tn.uma.isamm.spring.tp1.entities.AppRole;
import tn.uma.isamm.spring.tp1.entities.AppUser;
import tn.uma.isamm.spring.tp1.metier.MetierVentes;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private MetierVentes mv;
	
	@Autowired
    private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username as principal , password, active as credentials from app_user where username=?")
	   	.authoritiesByUsernameQuery("select username as principal , role_name as role from app_user au, app_role ar, app_user_roles aur "
				+ "Where au.id=aur.app_user_id and ar.id=aur.roles_id and au.username=?")
		.passwordEncoder(bCryptPasswordEncoder)
		.rolePrefix("ROLE_");		
	}
 
	
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(new UserDetailsService() {			
//			@Override
//			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//				AppUser appUser  = mv.getUserByLogin(username);
//				ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
//				appUser.getRoles().forEach(role->{
//					roles.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
//				});
//				return new User(appUser.getUsername(), appUser.getPassword(), roles);
//			}
//		});				
//	}


	public void configure(HttpSecurity http) throws Exception {	
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/user/*").hasAnyRole("USER", "ADMIN");
		http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");		
	}
}
