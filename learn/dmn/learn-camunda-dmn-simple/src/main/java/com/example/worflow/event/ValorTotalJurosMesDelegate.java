package com.example.worflow.event;


import com.example.worflow.Taxa;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;

@Named("calculaBear")
public class ValorTotalJurosMesDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        String estado = execution.getVariable("estado").toString();
        long principal = (long) execution.getVariable("valor");
        double taxa = (double) execution.getVariable("taxa");
        long meses = (long) execution.getVariable("quantidademeses");

        double montante = principal * Math.pow((1 + taxa), meses);
        double juros = montante - principal;

        execution.setVariable("taxade_" + estado, taxa);
        execution.setVariable("valortotal", String.format("%1$.2f", montante));
        execution.setVariable("valorjuros", String.format("%1$.2f", juros));

        System.out.println(execution.getCurrentActivityName());
    }

}
