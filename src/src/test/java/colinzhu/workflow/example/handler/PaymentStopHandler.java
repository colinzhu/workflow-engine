package colinzhu.workflow.example.handler;

import colinzhu.workflow.Event;
import colinzhu.workflow.example.Payment;
import colinzhu.workflow.example.PaymentStopRequest;

import java.util.function.BiFunction;

public class PaymentStopHandler implements BiFunction<Payment, PaymentStopRequest, Event> {
    @Override
    public Event apply(Payment payment, PaymentStopRequest request) {
        System.out.println("Stop payment comment: " + request.getComment());
        return new Event("STOPPED", null);
    }
}
