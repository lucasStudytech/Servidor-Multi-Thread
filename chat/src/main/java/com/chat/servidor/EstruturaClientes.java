package com.chat.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

//! Essa class vai vira uma estrtura de dados para armazenar informações do cliente
public class EstruturaClientes implements Runnable {

    //! Criar uma estrututra de dados da classe ClientesInfo
    private final List<EstruturaClientes> clientes;

    private final String nome;
    private final int id;
    private static int IdCouter = 0;
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;

    //!construtor da classe ClientesInfo
    public EstruturaClientes(Socket socket, PrintWriter out, BufferedReader in, String nome, List<EstruturaClientes> clientes) {

        this.id = IdCouter++; //!incrementa o id do cliente, cada cliente tem um id unico
        this.socket = socket;  //! pega o socket do cliente
        this.out = out; //! pega o output do cliente
        this.in = in;   //! pega o input do cliente
        this.nome = nome;   //! pega o nome do cliente
        this.clientes = clientes; //! pega a lista de clientes

    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    //! o printwriter é um objeto que escreve texto em um arquivo
    //! o out e para enviar mensagens para o cliente
    public PrintWriter getOut() {
        return out;
    }

    public Socket getSocket() {
        return socket;
    }

    // Métodos sincronizados para manipular a lista de clientes
    public synchronized void addClient() {
        //?this uma instancia atual da do cliente
        //!adiciona o cliente na lista de clientes
        clientes.add(this);

    }

    public synchronized void removeClient() {
        for (EstruturaClientes  cliente : clientes) {
            if (cliente.getId() == this.id) {
                clientes.remove(cliente);
                break; // Parar o loop após remover o cliente
            }
        }
    }

    public synchronized void broadcastMessage(String message) {
        for (EstruturaClientes  cliente : clientes) {
            //! Envia a mensagem para todos os clientes conectados, exceto o cliente que enviou a mensagem
            //! e apenas se o socket do cliente estiver aberto
            if (cliente != this && !cliente.getSocket().isClosed()) {
                cliente.getOut().println(message);
            }
        }
    }

    @Override
    public void run() {

        try {

            // Informar que o cliente se conectou
            broadcastMessage(nome + " entrou no chat.");

            // Ler e retransmitir mensagens
            String message;
            // Ler e retransmitir mensagens
            while ((message = in.readLine()) != null) {
                //System.out.println(nome + ": " + message);
                broadcastMessage(nome + ": " + message);
            }

        } catch (IOException e) {
            System.out.println("Erro na comunicação com o cliente: " + e.getMessage());
        } finally {
            // Remover o cliente da lista e fechar conexões
            removeClient();
            broadcastMessage(nome + " saiu do chat.");
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o socket do cliente: " + e.getMessage());
            }
        }
    }

}
