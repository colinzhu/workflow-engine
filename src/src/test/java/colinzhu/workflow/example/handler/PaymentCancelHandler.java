package colinzhu.workflow.example.handler;

import colinzhu.workflow.Event;
import colinzhu.workflow.example.Payment;
import colinzhu.workflow.example.PaymentCancelRequest;

import java.util.function.BiFunction;

public class PaymentCancelHandler implements BiFunction<Payment, PaymentCancelRequest, Event> {
    @Override
    public Event apply(Payment payment, PaymentCancelRequest request) {
        return new Event("CANCELLED");
    }
}
