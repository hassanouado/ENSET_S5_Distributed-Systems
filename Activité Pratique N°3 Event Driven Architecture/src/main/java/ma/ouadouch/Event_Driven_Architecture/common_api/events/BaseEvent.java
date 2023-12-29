package ma.ouadouch.Event_Driven_Architecture.common_api.events;

import lombok.Getter;

public abstract class BaseEvent<T> {
    @Getter
    private T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}
