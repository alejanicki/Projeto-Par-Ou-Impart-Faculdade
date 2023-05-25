import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {
    public static void main(String[] args) {
        try {
            // Criação do socket do servidor
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Aguardando conexão do Jogadores...");

            // Socket Jogador 1 e aceitação
            Socket PlayerOneSocket = serverSocket.accept();
            System.out.println("Jogador 1 conectado.");

            // Reader e Writer Jogador 1
            BufferedReader PlayerOneIn = new BufferedReader(new InputStreamReader(PlayerOneSocket.getInputStream()));
            PrintWriter PlayerOneOut = new PrintWriter(PlayerOneSocket.getOutputStream(), true);

            // Pergunta modo de jogo
            PlayerOneOut.println("Gostaria de Jogar Singleplayer ou Multiplayer ? (s/m)");
            String playerOneGameMode = PlayerOneIn.readLine();

            // Jogar Par ou Impar c/ máquina
            if (playerOneGameMode.equals("s")) {

                while (true) {
                    PlayerOneOut.println("Jogador 1, escolha um numero de 0 a 5: ");
                    int PlayerOneNum = Integer.parseInt(PlayerOneIn.readLine());
                    System.out.println("Numero Jogador 1: " + PlayerOneNum);

                    PlayerOneOut.println("Jogador 1, par ou impar? (p/i): ");
                    String PlayerOneChoice = PlayerOneIn.readLine();
                    System.out.println("Escolha Jogador 1: " + PlayerOneChoice);

                    Random random = new Random();
                    int randomNumber = random.nextInt(6);

                    List<String> letters = new ArrayList<>();
                    letters.add("p");
                    letters.add("i");
                    random = new Random();
                    int randomIndex = random.nextInt(letters.size());
                    String randomLetter = letters.get(randomIndex);

                    int result = PlayerOneNum + randomNumber;
                    PlayerOneOut.println("Jogador 1: " + "Numero - " + PlayerOneNum + "Escolha - " + PlayerOneChoice);
                    PlayerOneOut.println("Jogador 2: " + "Numero - " + randomNumber + "Escolha - " + randomLetter);
                    PlayerOneOut.println("Total: " + PlayerOneNum + " + " + randomNumber + " = " + result);

                    boolean isResultEven = result % 2 == 0;

                    if (PlayerOneChoice.contentEquals(randomLetter)) {
                        PlayerOneOut.println("Empatou");
                        System.out.println("Empatou");
                    } else if ((PlayerOneChoice.equals("p") && isResultEven)
                            || (PlayerOneChoice.equals("i") && !isResultEven)) {
                        PlayerOneOut.println("Jogador 1 venceu!");
                        System.out.println("Jogador 1 venceu!");
                    } else {
                        PlayerOneOut.println("Jogador 2 venceu!");
                        System.out.println("Jogador 2 venceu!");
                    }

                    PlayerOneOut.println("Deseja jogar novamente? (s/n): ");
                    String PlayerOneReset = PlayerOneIn.readLine();

                    if (PlayerOneReset.equals("n")) {
                        PlayerOneOut.println("FIM");
                        break;
                    }
                }
            }

            PlayerOneOut.println("Aguardando Jogador 2");

            // Socket Jogador 2 e aceitação
            Socket PlayerTwoSocket = serverSocket.accept();
            System.out.println("Jogador 2 conectado.");

            // Reader e Writer Jogador 2
            BufferedReader PlayerTwoIn = new BufferedReader(new InputStreamReader(PlayerTwoSocket.getInputStream()));
            PrintWriter PlayerTwoOut = new PrintWriter(PlayerTwoSocket.getOutputStream(), true);

            // Jogar Par ou Impar c/ dois jogadores
            while (true) {
                PlayerOneOut.println("Jogador 1, escolha um numero de 0 a 5: ");
                int PlayerOneNum = Integer.parseInt(PlayerOneIn.readLine());
                System.out.println("Numero Jogador 1: " + PlayerOneNum);

                PlayerOneOut.println("Jogador 1, par ou impar? (p/i): ");
                String PlayerOneChoice = PlayerOneIn.readLine();
                System.out.println("Escolha Jogador 1: " + PlayerOneChoice);

                PlayerTwoOut.println("Jogador 2, escolha um numero de 0 a 5: ");
                int PlayerTwoNum = Integer.parseInt(PlayerTwoIn.readLine());
                System.out.println("Numero Jogador 2: " + PlayerTwoNum);

                PlayerTwoOut.println("Jogador 2, par ou impar? (p/i): ");
                String PlayerTwoChoice = PlayerTwoIn.readLine();
                System.out.println("Escolha Jogador 2: " + PlayerTwoChoice);

                int result = PlayerOneNum + PlayerTwoNum;

                PlayerOneOut.println("Jogador 1: " + "Numero - " + PlayerOneNum + ", Escolha - " + PlayerOneChoice);
                PlayerOneOut.println("Jogador 2: " + "Numero - " + PlayerTwoNum + ", Escolha - " + PlayerTwoChoice);

                PlayerTwoOut.println("Jogador 1: " + "Numero - " + PlayerOneNum + ", Escolha - " + PlayerOneChoice);
                PlayerTwoOut.println("Jogador 2: " + "Numero - " + PlayerTwoNum + ", Escolha - " + PlayerTwoChoice);

                PlayerOneOut.println("Total: " + PlayerOneNum + " + " + PlayerTwoNum + " = " + result);
                PlayerTwoOut.println("Total: " + PlayerOneNum + " + " + PlayerTwoNum + " = " + result);

                boolean isResultEven = result % 2 == 0;

                if (PlayerOneChoice.contentEquals(PlayerTwoChoice)) {
                    PlayerOneOut.println("Empatou");
                    PlayerTwoOut.println("Empatou");
                } else if ((PlayerOneChoice.equals("p") && isResultEven)
                        || (PlayerOneChoice.equals("i") && !isResultEven)) {
                    PlayerOneOut.println("Jogador 1 venceu!");
                    PlayerTwoOut.println("Jogador 1 venceu!");
                } else {
                    PlayerOneOut.println("Jogador 2 venceu!");
                    PlayerTwoOut.println("Jogador 2 venceu!");
                }

                PlayerOneOut.println("Deseja jogar novamente? (s/n): ");
                String PlayerOneReset = PlayerOneIn.readLine();

                PlayerTwoOut.println("Deseja jogar novamente? (s/n): ");
                String PlayerTwoReset = PlayerTwoIn.readLine();

                if ((PlayerOneReset.equals("n")) || (PlayerTwoReset.equals("n"))) {
                    PlayerOneOut.println("FIM");
                    PlayerTwoOut.println("FIM");
                    break;
                }
            }

            // Fechar sockets
            PlayerOneSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
