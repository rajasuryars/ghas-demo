import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {
  // initialize input and output stream
    private static BufferedReader bufferedReader = null;
    private static DataInputStream dataInputStream = null;
    private static DataOutputStream dataOutputStream = null;

    public static void main(String[] args) {
        // establish a connection to target ip address and port
        try (Socket socket = new Socket("10.176.69.32", 5000)) {
            // takes input from client-side terminal
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String messageLine = "";
            try {
                System.out.print("Enter message: ");
                messageLine = bufferedReader.readLine();
                // send message to create directory
                dataOutputStream.writeUTF(messageLine);
            } catch(IOException i) {
                System.out.println(i);
            }
            try {
                // receive acknowledgement from server
                messageLine = dataInputStream.readUTF();
                System.out.println("Message from server: " + messageLine);
            } catch(IOException i) {
                System.out.println(i);
            }
            // send first file F1 to server
            System.out.println("Sending file F1 to server...");
            sendFile("D1/F1");
            // send second file F2 to server
            System.out.println("Sending file F2 to server...");
            sendFile("D1/F2");
            try {
                System.out.print("Enter message: ");
                messageLine = bufferedReader.readLine();
                // send end of session message
                dataOutputStream.writeUTF(messageLine);
            } catch(IOException i) {
                System.out.println(i);
            }
            // close the connection
            bufferedReader.close();
            dataOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendFile(String path) throws Exception {
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        dataOutputStream.writeLong(file.length());
        byte[] buffer = new byte[4 * 1024];
        // overcome the data size limitation on TCP sockets by sending small packets of data
        while ((bytes = fileInputStream.read(buffer)) != -1) {
            dataOutputStream.write(buffer, 0, bytes);
            dataOutputStream.flush();
        }
        System.out.println("File " + path.split("/", 2)[1] + " sent successfully!");
        fileInputStream.close();
    }
}
