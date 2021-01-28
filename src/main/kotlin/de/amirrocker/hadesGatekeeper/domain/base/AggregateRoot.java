package de.amirrocker.hadesGatekeeper.domain.base;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AggregateRoot {

    private UUID id;
    private int version = -1;

    private final ArrayList<Event<?>> changes = valueOf();

    public List<Event<?>> getChanges() {
        return changes;
    }

    protected void loadsFromHistory(List<Event> history) {
        history.forEach(event -> {
            event.applyOn(this);
            changes.add(event);
            version += 1;
        });
    }

    protected void applyChange(Event event) {
        event.applyOn(this);
        changes.add(event);
    }


     /* TODO make something better up ... */
     private ArrayList valueOf() {
         return new ArrayList();
     }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
