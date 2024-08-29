

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteDois {

    public static void main(String[] args) {

        //!define o endereço do servidor
        final String SERVER_ADDRESS = "localhost"; 
        final int SERVER_PORT = 3030; 

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

             //!canal de entrada de dados do servidor
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

           BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));){ //aqui começa o try 


             
           //!cria e inicia a thread para ler mensagens do servidor
            Mensagem mensagem = new Mensagem(in);
            Thread Thread = new Thread(mensagem);
            Thread.start();

            //! Enviar mensagens para o servidor
            System.out.println("Digite suas mensagens para enviar ao servidor:");
            System.out.println("Digite 'Sair' para encerrar a conexão");

            //!pega a mensagem do usuario
            String userInput;
            //!loop para enviar mensagens
            while ((userInput = userInputReader.readLine()) != null) {
                out.println(userInput);
                 
            }

        } catch (IOException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }


}