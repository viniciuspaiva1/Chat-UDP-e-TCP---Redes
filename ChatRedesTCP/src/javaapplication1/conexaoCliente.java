/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Cleita Moura
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class conexaoCliente implements Runnable {

    private String nomeCliente;
    private Scanner leitor;
    private PrintWriter escritor;
    private JavaApplication1 servidor;

    public conexaoCliente(Scanner entradacliente, PrintWriter saidaCliente, JavaApplication1 servidor) {
        this.leitor = entradacliente;
        this.servidor = servidor;
        this.escritor = saidaCliente;
    }

    public void run() {
        // pega o nome do cliente
        this.escritor.println("Digite seu nome: ");
        this.escritor.flush();
        this.nomeCliente = leitor.nextLine();
        this.servidor.adicionarCliente(this.nomeCliente, this.escritor);
        // quando chegar uma msg, distribui para todos
        
        while (leitor.hasNextLine()) {
            servidor.mensagem(this.nomeCliente, leitor.nextLine());
        }
    }
}
