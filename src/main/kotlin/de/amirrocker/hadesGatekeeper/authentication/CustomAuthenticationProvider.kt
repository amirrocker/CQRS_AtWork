package de.amirrocker.hadesGatekeeper.authentication

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class CustomAuthenticationProvider : AuthenticationProvider {

    val logger = LoggerFactory.getLogger(this::class.java)
    val users = mutableListOf<User>()

    @PostConstruct
    fun init() {
        users.add(User("aton", "bton", "ADMIN"))
        users.add(User("cton", "dton", "ADMIN"))
    }

    override fun authenticate(p0: Authentication): Authentication {
        val name = p0.name
        val password = p0.credentials.toString()

        val exitedUser = users.find {
            it.index(name, password)
        }

        if(exitedUser==null) {
            logger.error("Bad credentials - auth failed for $exitedUser")
            throw BadCredentialsException("Authentication failed for $exitedUser")
        }

        /* find out exitedUser */
        val grantedAuthorities = mutableListOf<GrantedAuthority>()
        grantedAuthorities.add(SimpleGrantedAuthority(exitedUser.role))
        val auth = UsernamePasswordAuthenticationToken(name, password, grantedAuthorities)

        logger.info("Successfull authentication for user $name")
        return auth
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }

    class User(
            val name:String,
            val password: String,
            val role: String
    ) {
        fun index(name:String, password: String): Boolean {
            return this.name.equals(name) && this.password.equals(password)
        }
    }

}