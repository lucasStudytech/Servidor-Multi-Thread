import java.io.BufferedReader;
import java.io.IOException;




public class Mensagem implements Runnable {
      
       final BufferedReader in;

        public Mensagem(BufferedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            String message;
            try {
                     //!loop para ler mensagens
                while ((message = in.readLine()) != null) {

                 
                    System.out.println( message);
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler mensagens: " + e.getMessage());
            }
        }
    }