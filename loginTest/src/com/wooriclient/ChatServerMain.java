package com.wooriclient;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//채팅 서버 메인
public class ChatServerMain {
	int port;					//서버의 PORT 번호
	ServerSocket serverSocket;	//서버 소켓
	
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
			System.out.println("서버 Start.");
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("서버 시작시 오류.");
		}
		
		while (true) {
			try {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress().getHostAddress() + " 접속.");
				
				thread = new ChatServerThread(socket);
				new Thread(thread).start();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

