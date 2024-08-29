

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteDois {

    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost"; // Endereço do servidor
        final int SERVER_PORT = 3030; // Porta do servidor

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            // Cria e inicia a thread para ler mensagens do servidor
            Mensagem mensagem = new Mensagem(in);
            Thread readThread = new Thread(mensagem);
            readThread.start();

            // Enviar mensagens para o servidor
            System.out.println("Digite suas mensagens para enviar ao servidor:");
            System.out.println("Digite 'Sair' para encerrar a conexão");
            String userInput;
            while (true) {
                userInput = scanner.nextLine();
                out.println(userInput);
                if ("Sair".equalsIgnoreCase(userInput)) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }

   
}
