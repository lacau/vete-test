package io.redspark;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Allow all urls for the sake of test
        // Remove after configure spring security and
        // create users authentication over database and etc...
        // Will not be done in a test project
        http.authorizeRequests()
                .antMatchers("/*")
                .permitAll();
    }
}