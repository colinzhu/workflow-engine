package colinzhu.workflow;

import lombok.Data;

import java.util.function.BiFunction;

@Data
public class Rule<T> {
    private String event;
    private String startStatus;
    private boolean auto;
    private String handlerClassName;
    private BiFunction<T, Object, String> handler;
    private boolean persistStatus;
}
