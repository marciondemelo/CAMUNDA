package workflow.event;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class LembreteEnvioDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("========================================================");
        System.out.println("ENVIE O PRODUTO DO PEDIDO: " + execution.getVariable("pedido"));
        System.out.println("========================================================");
    }
}
