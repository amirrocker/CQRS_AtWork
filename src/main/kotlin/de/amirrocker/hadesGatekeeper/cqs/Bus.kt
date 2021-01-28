package de.amirrocker.hadesGatekeeper.cqs

interface Bus {

    fun <R, C: Command<R>> executeCommand(command:C):R
    fun <R, Q: Query<R>> executeQuery(query:Q):R

}
