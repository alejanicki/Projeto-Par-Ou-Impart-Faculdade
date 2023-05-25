import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            //Criação do socket do servidor 
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Aguardando conexão do Jogadores...");

            //Socket Jogador 1 e aceitação
            Socket PlayerOneSocket = serverSocket.accept();
            System.out.println("Jogador 1 conectado.");

            //Reader e Writer Jogador 1
            BufferedReader PlayerOneIn = new BufferedReader(new InputStreamReader(PlayerOneSocket.getInputStream()));
            PrintWriter PlayerOneOut = new PrintWriter(PlayerOneSocket.getOutputStream(), true);

            //Fechar sockets
            PlayerOneSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
