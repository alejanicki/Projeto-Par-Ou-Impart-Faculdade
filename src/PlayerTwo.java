import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerTwo {
    public static void main(String[] args) {
        try {
            // Conecta ao servidor
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Conectado ao servidor.");

            // Reader e Writer Jogador 2
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            // Lógica de comunicação c/ servidor para jogar
            while (true) {
                // Recebe "Escolher Numero de 0 a 5"
                String serverMessage = in.readLine();
                System.out.println(serverMessage);
                int playerNum = Integer.parseInt(consoleInput.readLine());
                out.println(playerNum);

                // Recebe "Escolher impaar ou par"
                serverMessage = in.readLine();
                System.out.println(serverMessage);
                String playerChoice = consoleInput.readLine();
                out.println(playerChoice);

                // Recebe "Escolhas Jogador 1"
                serverMessage = in.readLine();
                System.out.println(serverMessage);

                // Recebe "Escolhas Jogador 2"
                serverMessage = in.readLine();
                System.out.println(serverMessage);

                // Recebe "Total"
                serverMessage = in.readLine();
                System.out.println(serverMessage);

                // Recebe resultado do servidor
                serverMessage = in.readLine();
                System.out.println(serverMessage);

                // Recebe jogar novamente do servidor
                serverMessage = in.readLine();
                System.out.println(serverMessage);
                String playAgain = consoleInput.readLine();
                out.println(playAgain);

                // Valida saida
                if (playAgain == "n") {
                    serverMessage = in.readLine();
                    System.out.println(serverMessage);
                    break;
                }
            }

            // Fechar Socket
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}