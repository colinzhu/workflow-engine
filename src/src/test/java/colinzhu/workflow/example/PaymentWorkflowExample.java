package colinzhu.workflow.example;

import colinzhu.workflow.WorkflowEngine;

public class PaymentWorkflowExample {
    /*
event	startStatus	auto	handlerClassName	persistStatus
SAVE	NONE	TRUE	colinzhu.workflow.example.handler.PaymentSaveHandler	TRUE
CHECK	SAVED	TRUE	colinzhu.workflow.example.handler.PaymentCheckHandler	FALSE
CLEAR_EXP	BLOCKED	FALSE	colinzhu.workflow.example.handler.PaymentClearExpHandler	TRUE
STOP	BLOCKED	FALSE	colinzhu.workflow.example.handler.PaymentStopHandler	TRUE
CANCEL	BLOCKED	FALSE	colinzhu.workflow.example.handler.PaymentCancelHandler	TRUE
*/
// [{"event":"SAVE","startStatus":"NONE","auto":"TRUE","handlerClassName":"colinzhu.workflow.example.handler.PaymentSaveHandler","persistStatus":"TRUE"},{"event":"CHECK","startStatus":"SAVED","auto":"TRUE","handlerClassName":"colinzhu.workflow.example.handler.PaymentCheckHandler","persistStatus":"FALSE"},{"event":"CLEAR_EXP","startStatus":"BLOCKED","auto":"FALSE","handlerClassName":"colinzhu.workflow.example.handler.PaymentClearExpHandler","persistStatus":"TRUE"},{"event":"STOP","startStatus":"BLOCKED","auto":"FALSE","handlerClassName":"colinzhu.workflow.example.handler.PaymentStopHandler","persistStatus":"TRUE"},{"event":"CANCEL","startStatus":"BLOCKED","auto":"FALSE","handlerClassName":"colinzhu.workflow.example.handler.PaymentCancelHandler","persistStatus":"TRUE"}]

    private static final String RULE = "[{\"event\":\"SAVE\",\"startStatus\":\"NONE\",\"auto\":true,\"handlerClassName\":\"colinzhu.workflow.example.handler.PaymentSaveHandler\",\"persistStatus\":true},{\"event\":\"CHECK\",\"startStatus\":\"SAVED\",\"auto\":true,\"handlerClassName\":\"colinzhu.workflow.example.handler.PaymentCheckHandler\",\"persistStatus\":false},{\"event\":\"CLEAR_EXP\",\"startStatus\":\"BLOCKED\",\"auto\":false,\"handlerClassName\":\"colinzhu.workflow.example.handler.PaymentClearExpHandler\",\"persistStatus\":true},{\"event\":\"STOP\",\"startStatus\":\"BLOCKED\",\"auto\":false,\"handlerClassName\":\"colinzhu.workflow.example.handler.PaymentStopHandler\",\"persistStatus\":true},{\"event\":\"CANCEL\",\"startStatus\":\"BLOCKED\",\"auto\":false,\"handlerClassName\":\"colinzhu.workflow.example.handler.PaymentCancelHandler\",\"persistStatus\":true}]";

    public static void main(String[] args) throws Exception {
        WorkflowEngine<Payment> engine = new WorkflowEngine(RULE);
        Payment payment = new Payment();
        payment.setStatus("NONE");

        engine.process(payment, null);
    }
}
