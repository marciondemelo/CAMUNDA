package com.example.worflow.event;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.lang.Exception;

public class Calcula implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        long valor = (long) execution.getVariable("valor");
        long divisao = (long) execution.getVariable("divisao");
        double result = (double)valor / (double)divisao;

        if (isInteiro(result)) {
            execution.setVariable("resultado", result);
            execution.setVariable("diferenca", "0.00");
            execution.setVariable("error", false);
        }else{
            execution.setVariable("error", true);
            throw new BpmnError("Divisao_inteira");
        }
    }

    public boolean isInteiro(double valor) {
        if (valor > Math.floor(valor)) {
            return false;
        } else {
            return true;
        }
    }
}
