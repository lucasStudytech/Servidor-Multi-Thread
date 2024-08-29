package com.chat.servidor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



public class Servidor {
    
    
    //!define a porta do servidor
    private static final int port = 3030;

    //?cria uma lista de clientes e armazena os clientes conectados
    private static final List<ClientesInfo> clientes = new ArrayList<>();

    public static void main(String[] args) {
        

        //!inicia o seervidor em uma porta especifica
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            

             System.out.println("Executando Servidor na Porta: " + port);
            System.out.println("CTRL+C para encerrar o servidor");

             //!um loop para esperar a conexão de clientes
            while (true) {

                 //!dentro do loop ele aceita a conexão do cliente
                Socket clientSocket = serverSocket.accept();

                 //!pega o ip do cliente e imprime na tela
                String clientIp = clientSocket.getInetAddress().getHostAddress();
                System.out.println("Novo Cliente conectado: " + clientIp);


                 //!leutra de dados do cliente
                InputStream input = clientSocket.getInputStream();

                 //!joga os dados do cliente em um buffer
                 BufferedReader in = new BufferedReader(new InputStreamReader(input));

                //!canal de saida de dados do cliente
                OutputStream output = clientSocket.getOutputStream();
                PrintWriter out = new PrintWriter(output, true);

                 //!pega o nome do cliente
                String nome = clientIp;
                              
                 //!cria um objeto do tipo ClientesInfo e passa os parametros
                ClientesInfo cliente = new ClientesInfo(clientSocket, out, in, nome, clientes);
                
                //!adiciona o cliente na lista de clientes conectados
                cliente.addClient();

                //! Enviar uma mensagem de boas-vindas para o cliente
                cliente.broadcastMessage(nome +  " entrou no chat");

                //! Iniciar uma nova thread para o cliente
                Thread thread = new Thread(cliente);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }

}
