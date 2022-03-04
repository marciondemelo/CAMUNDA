package com.example.worflow.event;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Arrays;

public class DoaDiferenca implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        long valor = (long) execution.getVariable("valor");
        long divisao = (long) execution.getVariable("divisao");
        double result =  ((double)valor / divisao);
        double diferenca = result - Math.floor(result);
        execution.setVariable("resultado",  Math.floor(result));
        execution.setVariable("diferenca", diferenca);
    }
}
