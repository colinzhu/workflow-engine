package colinzhu.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.function.BiFunction;

public class WorkflowEngine<T extends Processable> {
    ObjectMapper objectMapper = new ObjectMapper();
    private final List<Rule<T>> rules;

    public WorkflowEngine(String ruleJson) throws JsonProcessingException {
        rules = objectMapper.readValue(ruleJson, new TypeReference<>() {});
        System.out.println(rules.size());
        for (Rule<T> rule : rules) {
            try {
                String className = rule.getHandlerClassName();
                BiFunction<T, Object, String> handler = (BiFunction<T, Object, String>) Class.forName(className).newInstance();
                rule.setHandler(handler);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void process(T processable, Object params) {
        for (Rule<T> rule : rules) {
            if (rule.getStartStatus().equals(processable.getStatus()) && rule.isAuto()) {
                rule.getHandler().apply(processable, params);
                System.out.println(rule.getHandler().getClass().getName() + " " + processable.getStatus());
                process(processable, params);
            } else {
                break;
            }
        }
    }
}
