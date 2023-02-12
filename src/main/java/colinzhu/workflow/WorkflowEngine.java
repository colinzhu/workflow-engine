package colinzhu.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class WorkflowEngine<T> {
    ObjectMapper objectMapper = new ObjectMapper();
    private final List<Rule<T>> rules;

    public WorkflowEngine(String ruleJson) {
        try {
            rules = objectMapper.readValue(ruleJson, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(rules.size());
        for (Rule<T> rule : rules) {
            try {
                String className = rule.getHandlerClassName();
                BiFunction<T, Object, Event> handler = (BiFunction<T, Object, Event>) Class.forName(className).getDeclaredConstructor().newInstance();
                rule.setHandler(handler);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void process(Event event, T processable) {
        Optional<Rule<T>> optionalRule = rules.stream().filter(rule -> event.getName().equals(rule.getEvent())).findFirst();
        Event nextEvent = null;
        if (optionalRule.isPresent()) {
            BiFunction<T, Object, Event> handler = optionalRule.get().getHandler();
            System.out.println("Rule found for event: " + event + " handler: " + handler);
            nextEvent = handler.apply(processable, event.getRequest());
            System.out.println("Next event: " + nextEvent);
            process(nextEvent, processable);
        } else {
            System.out.println("No rule found for event: " + event);
        }

    }
}
