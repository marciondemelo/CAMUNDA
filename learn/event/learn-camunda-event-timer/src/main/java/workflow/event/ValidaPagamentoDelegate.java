package workflow.event;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ValidaPagamentoDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String numeroPedido = execution.getVariable("pedido").toString();
        String filename = numeroPedido + ".txt";
        File file = new File(filename);

        if (file.exists()) {
            System.out.println("==================================================");
            System.out.println("ACHOOOOOOOUUUUUUU O ARQUIVO " + filename);
            System.out.println("==================================================");
            execution.setVariable("pago", true);
            FileReader fr = new FileReader(file.getPath());
            BufferedReader br = new BufferedReader(fr);
            String doc = "";
            while (br.ready()) {
                String linha = br.readLine();
                doc = "\n" + linha;
            }
            br.close();
        } else {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("NÃO EXISTE ARQUIVO DE NOME " + filename + " NA PASTA RAIZ DO PROJETO");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
            execution.setVariable("pago", false);
        }
    }
}
