package com;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;



public class ChatServerThread implements Runnable {
	public static ArrayList<ChatServerThread> chatList = new ArrayList<ChatServerThread>();		//�������� Ŭ���̾�Ʈ ���
	public static ArrayList<String> usernames = new ArrayList<String>();		//������ �̸� ���
	
	Socket socket;
	ObjectInputStream input;
	ObjectOutputStream output;
	String username;
	String addr;
	
	
	public ChatServerThread(Socket socket) {
		try {
			this.socket = socket;
			//Ŭ���̾�Ʈ���� ��Ʈ�� ����
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			addr = socket.getInetAddress().getHostAddress();
		}
		catch (IOException e) {
			System.out.println(addr + "���� ���� ����.");
		}
		
		chatList.add(this);
	}

	//Ŭ���̾�Ʈ�κ����� ������ ��ٸ�.
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
				//���ο� ������ 
				case Data.FIRST_CONNECTION :
					message = name + "���� �����߽��ϴ�.";
					usernames.add(name);
					
					data.setMessage(message);
					data.setUsernames(usernames);
					
					broadCasting(data);
					break;
					
				//ä�� ���� ����
				case Data.CHAT_MESSAGE : 
					broadCasting(data);
					break;
					
				//���� ����
				case Data.DISCONNECTION : 
					message = name + "���� ������ �����߽��ϴ�.";
					
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

	//��ü �����ڿ��� ����
	public void broadCasting(Data data) {
		System.out.println("��ε�ĳ���� : Ŭ���̾�Ʈ �� : " + chatList.size());
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