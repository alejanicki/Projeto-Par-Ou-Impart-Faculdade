import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerOne {
    public static void main(String[] args) {
        try {
            // Conecta ao servidor
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Conectado ao servidor.");

            //Reader e Writer Jogador 1
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            //Lógica de comunicação c/ servidor para jogar
            while (true) {
                // Recebe mensagem inicial do servidor
                String serverMessage = in.readLine();
                System.out.println(serverMessage);
                int playerNum = Integer.parseInt(consoleInput.readLine());
                out.println(playerNum);

                // Recebe segunda mensagem do servidor
                serverMessage = in.readLine();
                System.out.println(serverMessage);
                String playerChoice = consoleInput.readLine();
                out.println(playerChoice);

                serverMessage = in.readLine();
                System.out.println(serverMessage);

                serverMessage = in.readLine();
                System.out.println(serverMessage);
                // Recebe total do servidor
                serverMessage = in.readLine();
                System.out.println(serverMessage);

                // Recebe resultado do servidor
                serverMessage = in.readLine();
                System.out.println(serverMessage);

                // Recebe jogar novamente do servidor
                serverMessage = in.readLine();
                String playAgain;

                System.out.println(serverMessage);
                playAgain = consoleInput.readLine();

                out.println(playAgain);

                if (playAgain == "n") {
                    serverMessage = in.readLine();
                    System.out.println(serverMessage);
                    break;
                }
            }

            //Fechar Socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
