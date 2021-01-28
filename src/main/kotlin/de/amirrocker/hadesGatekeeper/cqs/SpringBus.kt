package de.amirrocker.hadesGatekeeper.cqs

class SpringBus(
    val registry: Registry
) : Bus {

    override fun <R, C : Command<R>> executeCommand(command: C): R {
        val commandHandler = registry.getCommand(command.javaClass)
        return commandHandler.handle(command)
    }

    override fun <R, Q : Query<R>> executeQuery(query: Q): R {
        val queryHandler = registry.getQuery(query.javaClass)
        return queryHandler.handle(query)
    }
}
