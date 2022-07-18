import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.IOException;

public class Server {
    // initialize socket, input and output stream
    private static Socket clientSocket = null;
    private static DataInputStream dataInputStream = null;
    private static DataOutputStream dataOutputStream = null;

    public static void cleanDirectory(File file) throws Exception {
      // list all files in a directory
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (!Files.isSymbolicLink(f.toPath())) {
                    // recursively calling to remove subdirectories, if present
                    cleanDirectory(f);
                }
            }
        }
        // delete the file from a directory
        file.delete();
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            // starts server at port 5000 and waits for a connection
            System.out.println("Listening to port: 5000");
            clientSocket = serverSocket.accept();
            System.out.println(clientSocket + " connected");
            String messageLine = "";
            // takes input from the client socket
            dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            // reads message from client
            try {
                messageLine = dataInputStream.readUTF();
                System.out.println("Message from client: " + messageLine);
            } catch(IOException i) {
                System.out.println(i);
            }
            File file = new File("D1copy");
            // remove old directories and files
            cleanDirectory(file);
            // create a folder with name D1copy
            boolean bool = file.mkdir();
            try {
                messageLine = "D1copy folder created successfully!";
                System.out.println("Message to client: " + messageLine);
                dataOutputStream.writeUTF(messageLine);
            } catch(IOException i) {
                System.out.println(i);
            }
            if (bool) {
                System.out.println("Directory D1copy created successfully!");
                // receive first file F1 from client
                receiveFile("D1copy/F1");
                // receive second file F2 from client
                receiveFile("D1copy/F2");
                // receive end of session message
                try {
                    messageLine = dataInputStream.readUTF();
                    System.out.println("Message from client: " + messageLine);
                } catch(IOException i) {
                    System.out.println(i);
                }
            } else {
                System.out.println("Error creating directory D1copy!");
            }
            // close connection
            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void receiveFile(String fileName) throws Exception {
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        long size = dataInputStream.readLong();
        byte[] buffer = new byte[4 * 1024];
        // overcome the data size limitation on TCP sockets by receiving small packets of data
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes;
        }
        System.out.println("File " + fileName.split("/", 2)[1] + " received successfully!");
        fileOutputStream.close();
    }
}
