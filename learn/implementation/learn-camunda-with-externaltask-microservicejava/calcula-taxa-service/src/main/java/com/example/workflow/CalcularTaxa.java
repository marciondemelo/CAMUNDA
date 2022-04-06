package com.example.workflow;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.web.client.RestTemplate;

@Component
@ExternalTaskSubscription("calculo")
public class CalcularTaxa implements ExternalTaskHandler {
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        String estado = externalTask.getVariable("estado").toString();
        long principal = (long) externalTask.getVariable("valor");
        double taxa = taxaJurosPorEstado(estado);
        long meses = (long) externalTask.getVariable("quantidademeses");

        double montante = principal * Math.pow((1 + taxa), meses);
        double juros = montante - principal;


        VariableMap variables = Variables.createVariables();
        variables.put("taxade_" + estado, taxa);
        variables.put("valortotal", String.format("%1$.2f", montante));
        variables.put("valorjuros", String.format("%1$.2f", juros));
        externalTaskService.complete(externalTask, variables);
    }

    public double taxaJurosPorEstado(String estado) {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();

        Taxa entity = restTemplate.getForObject("http://localhost:9999/" + estado, Taxa.class);
        return entity.getValor();
    }
}

class Taxa {
    private double valor;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
