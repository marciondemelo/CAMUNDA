package workflow.event;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class MessageSubprocessoParcelado implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String estado = execution.getVariable("estado").toString();
        Double valor = (Double) execution.getVariable("valor");
        long meses = (long) execution.getVariable("meses");

        execution.getProcessEngineServices().getRuntimeService()
                .createMessageCorrelation("parcelado")
                .setVariable("estado", estado)
                .setVariable("valor", valor)
                .setVariable("meses", meses)
                .correlate();
    }
}
