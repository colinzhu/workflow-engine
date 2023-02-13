package colinzhu.workflow.example.handler;

import colinzhu.workflow.Event;
import colinzhu.workflow.example.Payment;
import colinzhu.workflow.example.PaymentClearExpRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;

@Slf4j
public class PaymentClearExpHandler implements BiFunction<Payment, PaymentClearExpRequest, Event> {
    @Override
    public Event apply(Payment payment, PaymentClearExpRequest request) {
        log.info("Clear exp comment: " + request.getComment());
        return new Event("BLOCKED");
    }
}
