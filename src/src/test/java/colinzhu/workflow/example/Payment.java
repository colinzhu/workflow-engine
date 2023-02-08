package colinzhu.workflow.example;

import colinzhu.workflow.Processable;
import lombok.Data;

@Data
public class Payment implements Processable {
    private String status;
}
