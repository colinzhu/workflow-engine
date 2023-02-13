package colinzhu.workflow;

import lombok.Data;

import java.util.function.BiFunction;

@Data
public class Rule<T> {
    private String eventName;
    private String handlerClassName;
    private BiFunction<T, Object, Event> handler;
}
