/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * @author qiqu
 */
public class TCPFile {
    static Integer fileNo = 0;

    public static void main(String args[]) {
        try {
            int serverPort = 7896; // the server port
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }

}

class Connection extends Thread {
    DataInputStream in;
    Socket clientSocket;
    FileOutputStream fileWriter;
    BufferedOutputStream bufferedWriter;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try {
            fileWriter = new FileOutputStream("./" + TCPFile.fileNo);
            synchronized (TCPFile.fileNo) {
                TCPFile.fileNo++;
            }
            bufferedWriter = new BufferedOutputStream(fileWriter);
            byte[] b=new byte[6000];
            int l=0;
            while (l!=-1) {
                l = in.read(b);
                if (l!=-1)bufferedWriter.write(b,0,l);
//                bufferedWriter.write(b);
            }
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            try {
                bufferedWriter.flush();
                fileWriter.flush();
                clientSocket.close();
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {/*close failed*/}
        }
    }
}
