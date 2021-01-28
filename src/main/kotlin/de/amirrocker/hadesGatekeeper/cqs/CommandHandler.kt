package de.amirrocker.hadesGatekeeper.cqs

/*
* Inspiration and guidance on how to implement CQRS / CQS and ES was found here:
* asc-lab java-cqrs-intro
* on github
* */
interface CommandHandler<R, C: Command<R>> {
    fun handle(command: C):R
}