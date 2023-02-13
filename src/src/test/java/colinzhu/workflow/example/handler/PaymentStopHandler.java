package colinzhu.workflow.example.handler;

import colinzhu.workflow.Event;
import colinzhu.workflow.example.Payment;
import colinzhu.workflow.example.PaymentStopRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;

@Slf4j
public class PaymentStopHandler implements BiFunction<Payment, PaymentStopRequest, Event> {
    @Override
    public Event apply(Payment payment, PaymentStopRequest request) {
        log.info("Stop payment comment: " + request.getComment());
        return new Event("STOPPED");
    }
}
