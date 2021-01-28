package de.amirrocker.hadesGatekeeper.configuration

import de.amirrocker.hadesGatekeeper.authentication.CustomAuthenticationProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.AuthenticationEntryPoint

@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var authProvider:CustomAuthenticationProvider

    @Autowired
    lateinit var authEntryPoint: AuthenticationEntryPoint

    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/admin", "/h2_console/**").hasRole("ADMIN").anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll()


        http.exceptionHandling().accessDeniedPage("/403")
        http.csrf().disable()
        http.headers().frameOptions().disable()
        http.httpBasic().authenticationEntryPoint(authEntryPoint)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authProvider)
    }
}