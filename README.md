# Workflow Engine

A design of workflow engine


### Feature

- Handle object status transitions according to the defined workflow rules
- Workflow rules can be defined in JSON, which can be converted from CSV
- Only a few files

### Rules
Rule = Conditions (if...) + Handler (then...)

### Example Rules
```
IF...............................THEN....................................................................
event       startStatus   auto   handlerClassName                                           persistStatus
SAVE        NONE          TRUE   colinzhu.workflow.example.handler.PaymentSaveHandler       TRUE
CHECK       SAVED         TRUE   colinzhu.workflow.example.handler.PaymentCheckHandler      FALSE
CLEAR_EXP   BLOCKED       FALSE  colinzhu.workflow.example.handler.PaymentClearExpHandler   TRUE
STOP        BLOCKED       FALSE  colinzhu.workflow.example.handler.PaymentStopHandler       TRUE
CANCEL      BLOCKED       FALSE  colinzhu.workflow.example.handler.PaymentCancelHandler     TRUE
```

### Process
1. Given there is an object, the status is A
2. When event is not provided, within all the rules, filter a rule which has startStatus=A and auto=TRUE
   1. if a rule is found, invoke the handler to handle the object, and update object status. e.g. B
      1. With new status B, continue with step 2
   2. if no rules are found, do nothing
3. When event is provided e.g. E1, within all the rules, filter a rule which has startStatus=A and event=E1, 
   1. if a rule is found, invoke the handler to handle the object, and update object status. e.g. B
      1. With new status B, continue with step 2
   2. if no rules are found, that's an exception

### Handler


