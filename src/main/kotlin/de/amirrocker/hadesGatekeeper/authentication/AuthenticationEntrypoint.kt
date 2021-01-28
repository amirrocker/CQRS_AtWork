package de.amirrocker.hadesGatekeeper.authentication

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.PrintWriter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationEntrypoint: BasicAuthenticationEntryPoint() {

    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, authException: AuthenticationException?) {
        super.commence(request, response, authException)

        response?.addHeader("www-Authenticate", "Basic realm=" + realmName)
        response?.status = HttpServletResponse.SC_UNAUTHORIZED
        val writer = response?.writer
        writer?.println("HTTP Status 401 - ${authException?.message}")
    }

    override fun afterPropertiesSet() {
        realmName = "amirrocker"
        super.afterPropertiesSet()
    }
}