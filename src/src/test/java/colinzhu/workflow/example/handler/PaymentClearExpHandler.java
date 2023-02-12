package colinzhu.workflow.example.handler;

import colinzhu.workflow.Event;
import colinzhu.workflow.example.Payment;
import colinzhu.workflow.example.PaymentClearExpRequest;

import java.util.function.BiFunction;

public class PaymentClearExpHandler implements BiFunction<Payment, PaymentClearExpRequest, Event> {
    @Override
    public Event apply(Payment payment, PaymentClearExpRequest request) {
        System.out.println("Clear exp comment: " + request.getComment());
        return new Event("BLOCKED");
    }
}
