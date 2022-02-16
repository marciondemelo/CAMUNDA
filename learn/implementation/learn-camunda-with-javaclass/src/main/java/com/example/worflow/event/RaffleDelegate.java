package com.example.worflow.event;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;

import java.util.Random;

public class RaffleDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var random = new Random();
        boolean result = random.nextBoolean();

        execution.setVariable("resultado", result);
    }
}
