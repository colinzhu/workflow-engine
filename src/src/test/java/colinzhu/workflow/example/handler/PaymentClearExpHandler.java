package colinzhu.workflow.example.handler;

import colinzhu.workflow.example.Payment;

import java.util.function.BiFunction;

public class PaymentClearExpHandler implements BiFunction<Payment, Object, String> {
    @Override
    public String apply(Payment payment, Object o) {
        return null;
    }
}
