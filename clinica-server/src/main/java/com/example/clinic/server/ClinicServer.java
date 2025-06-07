package com.example.clinic.server;

import java.net.ServerSocket;
import java.net.Socket;

public class ClinicServer {
    private static final int PORT = 5555;

    public static void main(String[] args) {
        System.out.println("Pornesc ClinicServer pe portul " + PORT + "...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket client = serverSocket.accept();
                new Thread(new ClientHandler(client)).start();
            }
        } catch (Exception e) {
        }
    }
}
