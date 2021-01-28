package de.amirrocker.hadesGatekeeper.cqs

interface QueryHandler<R, C: Query<R>> {
    fun handle(query:C):R
}