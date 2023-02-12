package colinzhu.workflow.example.handler;

import colinzhu.workflow.Event;
import colinzhu.workflow.example.Payment;

import java.util.function.BiFunction;

public class PaymentCheckHandler implements BiFunction<Payment, Object, Event> {
    @Override
    public Event apply(Payment payment, Object request) {
        return new Event("BLOCKED");
    }
}
