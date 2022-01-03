package org.worker.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author peiru wang
 * @date 2021/8/25
 */
public class BlockIO {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8090);

        System.out.println("ServerSocket Start: " + serverSocket.getInetAddress());

        while(true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("ClientSocket connect: " + clientSocket.getInetAddress());

            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            bw.write("please input...\r\n");
            bw.flush();
            try {
                while(true) {
                    String line = br.readLine();
                    if("end".equals(line)) {
                        break;
                    }
                    System.out.println("Client: " + line);
                    bw.write("Server: " + line + "\r\n");
                    bw.flush();
                }
            } finally {
                br.close();
                bw.close();
                clientSocket.close();
            }
        }
    }

}
