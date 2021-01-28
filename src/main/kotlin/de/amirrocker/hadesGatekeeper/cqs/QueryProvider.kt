package de.amirrocker.hadesGatekeeper.cqs

import org.springframework.context.ApplicationContext

class QueryProvider<H: QueryHandler<Any, Query<Any>>>(
    val applicationContext: ApplicationContext,
    val type: Class<H>
) {

    fun get():H =
        applicationContext.getBean(type)

}