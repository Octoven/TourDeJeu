package club.tourdejeu.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private String USER_BYNAME = "select username as principal, password as credentials, enabled from utilisateur where username = ?";
    private String AUTH_BYUSER = "select username as principal, role as role from role_utilisateur where username=?";
    private String ROLE_PREFIX = "ROLE_";

    // setting a password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	// configuring the authentication and access right though the user entries in
	// the database
	auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(USER_BYNAME)
		.authoritiesByUsernameQuery(AUTH_BYUSER).rolePrefix(ROLE_PREFIX).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	// setting a new login form to replace Spring Security default login page
	http.formLogin().loginPage("/login");
	// deleting JSESSIONID and destroying session on logout
	http.logout().deleteCookies("JSESSIONID").invalidateHttpSession(true);
	// setting acces rights to specific URLs paths
	http.authorizeRequests().antMatchers("/super/*").hasRole("SUPER");
	http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
	http.authorizeRequests().antMatchers("/membre/*").hasRole("USER");
	// setting a new access denied page
	http.exceptionHandling().accessDeniedPage("/403");

    }

}
