package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    /**
     * Simple bean that is used to encrypt passwords
     * @returns BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Autowired
    //private DataSource dataSource;

    /**
     * jdbc authentication that saves our user while he is logged in
     * verifying him to our dataSource(database)
     * then a simple query to search for users by role rather than user_roles
     * @param auth
     * @throws Exception
     */
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        /*auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .authoritiesByUsernameQuery("select username, role from user where username=?");*/

        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user").password("{noop}password").authorities("ROLE_USER")
                .and().withUser("isak").password(passwordEncoder().encode("isak"))
                .authorities("ROLE_USER", "ROLE_ADMIN");
    }

    /**
     * Handler for all our links,
     * important! must add new antmatcher with permissions needed if a new link is created (ie controller)
     * only an authenticated user (ie logged in user) can view myHome.
     * The most specific rules should go first, but don't for our case due to this being weird.
     * Please do not touch as this is very delicate
     * @param http
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/transaction/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/transaction", true)
                .permitAll()
                .and()
                .logout().permitAll()

                .and()
                .csrf().disable();
    }

    /**
     *
     * Allows all utils, resources, css, jsp files and images.
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception{
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/webapp/**", "/img/**");
    }

}