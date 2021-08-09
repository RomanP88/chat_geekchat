import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientTryWithResources {

    static final int SERVER_PORT = 8189;

    static  final String SERVER_ADDRESS = "localhost";

    public static void main(String[] args) {
        Socket socket = null;
        Scanner scanner = new Scanner(System.in);


        try {
            socket = new Socket(SERVER_ADDRESS,SERVER_PORT);
            System.out.println("Подключен к серверу " + socket.getRemoteSocketAddress());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());


            Thread threadReader = new Thread(()->{
                while(true){
                    try {
                        outputStream.writeUTF(scanner.nextLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            threadReader.setDaemon(true);
            threadReader.start();

            while (true) {

                String str = inputStream.readUTF();
                if (str.equals("/close")) {
                    System.out.println("Потеряно соединение с сервером");
                    outputStream.writeUTF("/close");
                    break;
                } else {
                    System.out.println("Сервер: " + str);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
