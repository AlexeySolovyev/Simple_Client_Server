import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        int count = 0;
        ServerSocket serverSocket = new ServerSocket(8000);  // создаем сервер с сокетом 8000
        while (true) {
            Socket clientSocket = serverSocket.accept(); // получаем подключение от клиента и создаем клиент
            System.out.println("Client accepted " + (++count));

            BufferedWriter writer = new BufferedWriter
                    (new OutputStreamWriter
                            (clientSocket.getOutputStream()));

            BufferedReader reader = new BufferedReader
                    (new InputStreamReader
                            (clientSocket.getInputStream()));

            String request = reader.readLine();
            String response = "#" + count + " ,your message length is " + request.length() + "\n";

            writer.write(response);
            writer.flush();

            reader.close();
            writer.close();
            clientSocket.close(); // закрываем клиент
        }
    }
}
