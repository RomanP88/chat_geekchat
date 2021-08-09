import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTryWithResources {


    static final int PORT = 8189;

    public static void main(String[] args) {
        Socket clientSocket = null;
        Scanner scanner = new Scanner(System.in);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Сервер Запущен");
            clientSocket = serverSocket.accept();

            System.out.println("Клиент подкдючен " + clientSocket.getRemoteSocketAddress());
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

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

            while (true){

                String str = inputStream.readUTF();
                if(str.equals("/close")){
                    System.out.println("Клиент покинул сервер");
                    outputStream.writeUTF("/close");
                    break;
                } else{
                    System.out.println("Клиент: " + str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }

    }
}
