

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteUm {

    public static void main(String[] args) {


        final String SERVER_ADDRESS = "localhost"; 
        final int SERVER_PORT = 3030; 

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

        //! pega os byte do socket e transforma em caracteres
        InputStreamReader input = new InputStreamReader(socket.getInputStream());

        //!buffer para armazenar os caracteres.
        //?Exemplo de leitura dos caracteres : " String line = in.readLine();"
        BufferedReader in = new BufferedReader(input);      

        //!escreve texto em formato para um fluxo de saída de caracteres
        //?Exemplo de escrita de caracteres: "out.println("Hello World");"
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        //!o System.in  é o fluxo de entrada padrão (teclado)
        //!InputStreamReader converte bytes do teclado em caracteres.
        //!bufferedReader é um objeto que armazena os caracteres
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));){ //aqui começa o try 
          

            // Cria e inicia a thread para ler mensagens do servidor
            Mensagem mensagem = new Mensagem(in);
            Thread Thread = new Thread(mensagem);
            Thread.start();

            // Enviar mensagens para o servidor
            System.out.println("Digite suas mensagens para enviar ao servidor:");
            System.out.println("Digite 'Sair' para encerrar a conexão");
            String userInput;

            while ((userInput = userInputReader.readLine()) != null) {
                out.println(userInput);
                
               
            }


        } catch (IOException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }

   
}
