package com.wooriclient;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;



public class ChatServerThread implements Runnable {
	public static ArrayList<ChatServerThread> chatList = new ArrayList<ChatServerThread>();		//접속중인 클라이언트 목록
	public static ArrayList<String> usernames = new ArrayList<String>();		//접속자 이름 목록
	
	Socket socket;
	ObjectInputStream input;
	ObjectOutputStream output;
	String username;
	String addr;
	
	
	public ChatServerThread(Socket socket) {
		try {
			this.socket = socket;
			//클라이언트와의 스트림 생성
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			addr = socket.getInetAddress().getHostAddress();
		}
		catch (IOException e) {
			System.out.println(addr + "과의 연결 실패.");
		}
		
		chatList.add(this);
	}

	//클라이언트로부터의 전송을 기다림.
	@Override
	public void run() {
		Data data = null;
		int state = 0;
		String name = null;
		String message = null;
		
		while (true) {
			try {
				data = (Data) input.readObject();
				state = data.getState();
				name = data.getName();
				System.out.println(name + " > status:" + state + ", message:" + data.getMessage());
				
				switch (state) {
				//새로운 접속자 
				case Data.FIRST_CONNECTION :
					message = name + "님이 접속했습니다.";
					usernames.add(name);
					
					data.setMessage(message);
					data.setUsernames(usernames);
					
					broadCasting(data);
					break;
					
				//채팅 내용 전송
				case Data.CHAT_MESSAGE : 
					broadCasting(data);
					break;
					
				//접속 종료
				case Data.DISCONNECTION : 
					message = name + "님이 접속을 종료했습니다.";
					
					usernames.remove(name);
					chatList.remove(this);
			
					input.close();
					output.close();
					socket.close();
					
					data.setMessage(message);
					data.setUsernames(usernames);
					
					broadCasting(data);
					break;
				}
				
			}
			catch (Exception e) {
				return;
			}
		}
	}

	//전체 접속자에게 전송
	public void broadCasting(Data data) {
		System.out.println("브로드캐스팅 : 클라이언트 수 : " + chatList.size());
		for (ChatServerThread thread : chatList) {
			try {
				thread.output.writeObject(data);
				thread.output.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}