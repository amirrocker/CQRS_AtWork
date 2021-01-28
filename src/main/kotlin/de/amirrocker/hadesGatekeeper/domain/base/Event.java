package de.amirrocker.hadesGatekeeper.domain.base;


import java.util.UUID;

public abstract class Event<T> {
    private UUID id = UUID.randomUUID();
    private int version = 1;

    protected abstract void applyOn(T aggregate);
}
