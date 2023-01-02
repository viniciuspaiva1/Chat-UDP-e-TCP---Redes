package javaapplication1;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JavaApplication1 {
	public static void main(String[] args) {
		// inicia o servidor
		new JavaApplication1(6668).executa();
	}
	private int porta;
        private int i=0;
	private HashMap<String, PrintWriter> mapaClientes;
	private ArrayList<String> nomes;
	public JavaApplication1(int porta) {
		this.porta = porta;
		this.mapaClientes = new HashMap<String, PrintWriter>();
		this.nomes = new ArrayList<String>();
	}
	public void executa() {
		try {
			ServerSocket servidor = new ServerSocket(this.porta);
			System.out.println("Porta " + servidor.getLocalPort() + " aberta!");
			while (true) {
                            if(i<2){
				// aceita um cliente
				Socket cliente = servidor.accept();
				// adiciona saida do cliente a lista
				PrintWriter ps = new PrintWriter(cliente.getOutputStream());
				// cria tratador de clientes numa nova thread
                                Scanner leitor = new Scanner (cliente.getInputStream());
				conexaoCliente tc = new conexaoCliente(leitor,
						ps, this);
				new Thread(tc).start();
                                i++;
                            }    
			}
		} catch (IOException e) {
			System.out.println("IOException " + e);
		}
	}
	public void mensagem(String nome, String msg) {
		// envia msg para todo mundo
		for (Map.Entry<String, PrintWriter> clientes : mapaClientes.entrySet()) {
			String chave = clientes.getKey();
			PrintWriter enviar = clientes.getValue();
			if (!chave.equals(nome)) {
				enviar.println(">>> " + nome + " diz: " + msg);
                                enviar.flush();
			}
		}
	}
	public void adicionarCliente(String nome, PrintWriter cliente) {
		this.mapaClientes.put(nome, cliente);
		this.nomes.add(nome);
		System.out.println("Nova Conexão: " + nome);
		for (Map.Entry<String, PrintWriter> clientes : mapaClientes.entrySet()) {
			String chave = clientes.getKey();
			PrintWriter enviar = clientes.getValue();
			if (!chave.equals(nome)) {
				enviar.println(nome + " se conectou!");
				enviar.println("Conectados: " + this.nomes.toString());
                                enviar.flush();
			} else {
				enviar.println("Bem-vindo " + nome);
				enviar.println("Conectados: " + this.nomes.toString());
                                enviar.flush();
			}
		}
	}
}
