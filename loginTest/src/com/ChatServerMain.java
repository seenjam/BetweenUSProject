package com;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//ä�� ���� ����
public class ChatServerMain {
	int port;					//������ PORT ��ȣ
	ServerSocket serverSocket;	//���� ����
	
	public static void main(String[] args) {
		ChatServerMain chatServer = new ChatServerMain(3333);
		chatServer.start();
	}
	
	public ChatServerMain(int port) {
		this.port = port;
	}
	
	public void start() {
		Socket socket = null;
		ChatServerThread thread = null;		
		
		try {
			System.out.println("���� Start.");
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("���� ���۽� ����.");
		}
		
		while (true) {
			try {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress().getHostAddress() + " ����.");
				
				thread = new ChatServerThread(socket);
				new Thread(thread).start();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

