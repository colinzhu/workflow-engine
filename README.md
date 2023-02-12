# Workflow Engine

A design of workflow engine

### Example Rules
```
IF...............................THEN....................................................................
event                   handlerClassName                                           outputEvent
RECEIVED                colinzhu.workflow.example.handler.PaymentSaveHandler       SAVED
SAVED                   colinzhu.workflow.example.handler.PaymentCheckHandler      BLOCKED, RELEASED
RQST_RECEIVED_EXP_CLEAR	colinzhu.workflow.example.handler.PaymentClearExpHandler   BLOCKED, RELEASED
RQST_RECEIVED_STOP      colinzhu.workflow.example.handler.PaymentStopHandler       STOPPED
RQST_RECEIVED_CANCEL    colinzhu.workflow.example.handler.PaymentCancelHandler     CANCELLED
BLOCKED
STOPPED
CANCELLED
RELEASED

```
