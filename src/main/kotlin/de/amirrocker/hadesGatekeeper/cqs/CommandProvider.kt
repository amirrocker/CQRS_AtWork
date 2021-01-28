package de.amirrocker.hadesGatekeeper.cqs

import org.springframework.context.ApplicationContext

class CommandProvider<H: CommandHandler<*, Command<*>>>(
    val applicationContext: ApplicationContext,
    val type: Class<H>
) {

    fun get():H =
        applicationContext.getBean(type)

}