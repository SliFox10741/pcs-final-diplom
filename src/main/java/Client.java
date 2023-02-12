import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        String answer;

        try (Socket socket = new Socket(ServerConfig.HOST, ServerConfig.PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {

            answer = in.readLine();
            System.out.println(answer);

            out.println(scanner.nextLine());
            answer = in.readLine();
            System.out.println(answer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
