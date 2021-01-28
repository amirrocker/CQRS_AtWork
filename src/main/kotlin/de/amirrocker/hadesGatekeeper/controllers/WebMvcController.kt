package de.amirrocker.hadesGatekeeper.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class WebMvcController {

    @GetMapping("")
    fun index(): String {
        return "home"
    }

    @GetMapping("/admin")
    fun admin(): String {
        return "admin"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/403")
    fun error403(): String {
        return "403"
    }

}