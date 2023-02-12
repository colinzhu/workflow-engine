package colinzhu.workflow.example;

import colinzhu.workflow.Event;
import colinzhu.workflow.WorkflowEngine;

public class PaymentWorkflowExample {
/*
event	handlerClassName	outputEvent
RECEIVED	colinzhu.workflow.example.handler.PaymentSaveHandler	SAVED
SAVED	colinzhu.workflow.example.handler.PaymentCheckHandler	BLOCKED, RELEASED
RQST_RECEIVED_EXP_CLEAR	colinzhu.workflow.example.handler.PaymentClearExpHandler	BLOCKED, RELEASED
RQST_RECEIVED_STOP	colinzhu.workflow.example.handler.PaymentStopHandler	STOPPED
RQST_RECEIVED_CANCEL	colinzhu.workflow.example.handler.PaymentCancelHandler	CANCELLED
BLOCKED
STOPPED
CANCELLED
RELEASED
*/

    private static final String RULE = """
            [{"event":"RECEIVED","handlerClassName":"colinzhu.workflow.example.handler.PaymentSaveHandler"},{"event":"SAVED","handlerClassName":"colinzhu.workflow.example.handler.PaymentCheckHandler"},{"event":"RQST_RECEIVED_EXP_CLEAR","handlerClassName":"colinzhu.workflow.example.handler.PaymentClearExpHandler"},{"event":"RQST_RECEIVED_STOP","handlerClassName":"colinzhu.workflow.example.handler.PaymentStopHandler"},{"event":"RQST_RECEIVED_CANCEL","handlerClassName":"colinzhu.workflow.example.handler.PaymentCancelHandler"}]
            """;

    public static void main(String[] args) throws Exception {
        WorkflowEngine<Payment> engine = new WorkflowEngine(RULE);
        Payment payment = new Payment();
        payment.setStatus("NONE");

        engine.process(new Event("RECEIVED"), payment);

        PaymentClearExpRequest paymentClearExpRequest = new PaymentClearExpRequest();
        paymentClearExpRequest.setComment("Clear exp abc");
        engine.process(new Event<>("RQST_RECEIVED_EXP_CLEAR", paymentClearExpRequest), payment);

        PaymentCancelRequest paymentCancelRequest = new PaymentCancelRequest();
        paymentCancelRequest.setComment("Canceled by system.");
        engine.process(new Event<>("RQST_RECEIVED_CANCEL", paymentCancelRequest), payment);

    }
}
