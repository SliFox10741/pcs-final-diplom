package Server;

import OtherClass.BooleanSearchEngine;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String... args) throws Exception {

        String word;
        BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));

        try (ServerSocket serverSocket = new ServerSocket(ServerConfig.PORT)) { // стартуем сервер один(!) раз
            System.out.println("Сервер запущен");
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    System.out.println("Подключён клиент " + socket.getPort());
                    out.println("Введите искомое слово: ");
                    word = in.readLine().toLowerCase();

                    System.out.println("(" + socket.getPort() + ") " + word);

                    out.println(engine.search(word));

                }catch (IOException e) {
                    throw new RuntimeException(e);

                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }

        // здесь создайте сервер, который отвечал бы на нужные запросы
        // слушать он должен порт 8989
        // отвечать на запросы /{word} -> возвращённое значение метода search(word) в JSON-формате
    }
}
