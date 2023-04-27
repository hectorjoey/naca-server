package fhi360.it.assetverify.config;

import fhi360.it.assetverify.auth.RestAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.userDetailsService());
        authProvider.setPasswordEncoder((PasswordEncoder) this.passwordEncoder());
        return authProvider;
    }

    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("asset")
                .password(this.passwordEncoder().encode((CharSequence) "admin"))
                .roles("ADMIN").and().withUser("user").password
                        (this.passwordEncoder().encode((CharSequence) "user")).roles("USER");
    }

    protected void configure(final HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().authenticationEntryPoint(this.restAuthenticationEntryPoint)
                .and().authorizeRequests().antMatchers("/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                .antMatchers("/delete/**").hasRole("ADMIN")
                .antMatchers("/updateUser").hasAnyRole("USER").anyRequest()
                .authenticated().and().formLogin().permitAll()
                .and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/403");
        http.csrf().disable();
    }
}