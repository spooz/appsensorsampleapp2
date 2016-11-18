package com.bartoszbalukiewicz.config;


import com.bartoszbalukiewicz.appsensor.security.context.AppSensorSecurityContextRepository;
import com.bartoszbalukiewicz.security.ip.BannedIpStore;
import com.bartoszbalukiewicz.security.ip.MemoryBannedIpStore;
import com.bartoszbalukiewicz.security.ip.SecurityIpFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Bartek on 18.09.2016.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    private BannedIpStore bannedIpStore;

    @Bean
    public SecurityContextRepository securityContextRepository(){
        return new AppSensorSecurityContextRepository();
    }

    @Bean
    public BannedIpStore getBannedIpStore() {
        this.bannedIpStore = new MemoryBannedIpStore();
       return bannedIpStore;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/img/*", "/font-awesome/*", "/register", "/test/*").permitAll().anyRequest().fullyAuthenticated().
                and().formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password").successHandler(authenticationSuccessHandler).failureUrl("/login?error").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll().and().securityContext().securityContextRepository(securityContextRepository());

        http.addFilterAfter(new SecurityIpFilter(bannedIpStore), SecurityContextPersistenceFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider).userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


}

