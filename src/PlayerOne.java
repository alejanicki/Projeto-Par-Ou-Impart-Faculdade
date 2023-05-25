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

            //Fechar Socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
