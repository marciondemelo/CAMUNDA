package com.example.workflow.event;

import com.example.workflow.Taxa;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class ValorTotalJurosMesDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        String estado = execution.getVariable("estado").toString();
        long principal = (long) execution.getVariable("valor");
        double taxa = taxaJurosPorEstado(estado);
        long meses = (long) execution.getVariable("quantidademeses");

        double montante = principal * Math.pow((1 + taxa), meses);
        double juros = montante - principal;

        execution.setVariable("taxade_" + estado, taxa);
        execution.setVariable("valortotal", String.format("%1$.2f", montante));
        execution.setVariable("valorjuros", String.format("%1$.2f", juros));
    }

    public double taxaJurosPorEstado(String estado) {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();

        Taxa entity = restTemplate.getForObject("http://localhost:9999/" + estado, Taxa.class);
        return entity.getValor();
    }

}
