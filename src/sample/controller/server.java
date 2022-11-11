package sample.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(52349);
        System.out.println("Server Running, Waiting for connections ...\n");
        int clientNumber = 0;
        while (true){
            Socket socket = server.accept();
            clientNumber++;
            System.out.println("Client #" + clientNumber + " Connected");
            DataInputStream reader = new DataInputStream(socket.getInputStream());
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            clientThread t = new clientThread(socket, reader, writer, clientNumber);
            t.start();
        }
    }
}
