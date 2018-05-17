package com.wooriclient;

/*--*

* ä�� ��GUI

--*/

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import VO.MemberVO;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ChatClientMain extends JFrame implements ActionListener, Runnable{


	String ip;
	int port;
	String username;

	Socket socket;
	ObjectInputStream input;
	ObjectOutputStream output;

	JTextArea outputText; // ä�� ���� ���â
	JScrollPane scroll; // ä�� ����â ��ũ�ѹ�
	JTextField input_tp; // ä�� ���� �Է�â

	private JPanel contentPane;
	private MemberVO myInfo = null; // ���� ����
	private MemberVO friendInfo = null; // ä���ϴ� ģ���� ����
	private JButton send_btn;

	private JTextPane Chat_tp;
	private JLabel withWho_lb;
	
	public static void main(String[] args) {
		//woorichat w = new woorichat("203.233.196.118", 3333);

		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() { try { woorichat
		 * frame = new woorichat(); frame.setVisible(true); } catch (Exception e) {
		 * e.printStackTrace(); } } });
		 */
	}

/*	public void gettingInfo(MemberVO myInfo, MemberVO friendInfo) {
		System.out.println(myInfo);
		System.out.println(friendInfo);
		this.myInfo = myInfo;
		this.friendInfo = friendInfo;
	}
	
	public void gettingIPandPORT(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}*/

	public ChatClientMain(MemberVO myInfo, MemberVO friendInfo,String ip, int port) {
		System.out.println("ChatClientMain 클래스로 들어옴.");
		
		this.myInfo = myInfo;
		this.friendInfo = friendInfo;
		this.ip = ip;
		this.port = port;
		System.out.println(myInfo);
		System.out.println(ip);
		
		//틀
		setBounds(100, 100, 352, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 336, 377);
		contentPane.add(panel);
		panel.setLayout(null);

		Chat_tp = new JTextPane();
		Chat_tp.setEditable(false);
		Chat_tp.setBounds(0, 0, 336, 351);
		panel.add(Chat_tp);

		
		
		
		outputText = new JTextArea();
		outputText.setLineWrap(true);

		scroll = new JScrollPane(outputText);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		getContentPane().add(scroll);
		
		
		
		withWho_lb = new JLabel(friendInfo.getMemID() + "님과의 대화");
		withWho_lb.setHorizontalAlignment(SwingConstants.CENTER);
		withWho_lb.setFont(new Font("����", Font.PLAIN, 10));
		withWho_lb.setBounds(10, 361, 211, 15);
		panel.add(withWho_lb);

		input_tp = new JTextField();
		input_tp.setBounds(10, 383, 235, 29);
		contentPane.add(input_tp);
		//inputText.addActionListener(this);

		send_btn = new JButton("send");
		send_btn.setBounds(251, 387, 85, 23);
		contentPane.add(send_btn);
		send_btn.addActionListener(this);

		//
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				disconnect();
			}
		});
		// 
		//UsernameDialog dialog = new UsernameDialog(this);
		//dialog.setVisible(true);
		setTitle("나 : (" + myInfo.getMemID() + ")");
		
		connect();
		setVisible(true);
	}

	// ��ȭ���� ���â�� �޼��� ���
	public void output(String msg) {
		outputText.append(msg + "\n");
		scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
	}

	// ���� ����
	public void connect() {
		try {
			socket = new Socket(ip, port);
			output("*** ������ ���ӵǾ����ϴ�.");

			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());

			new Thread(this).start();
			Data data = new Data(Data.FIRST_CONNECTION, username);
			output.writeObject(data);
		} catch (IOException e) {
			output("*** ���� ���� ����");
		}
	}

	// ���� ���� ����
	public void disconnect() {
		Data data = new Data(Data.DISCONNECTION, username);
		try {
			output.writeObject(data);
			output.flush();

			output.close();
			input.close();
			socket.close();
		} catch (Exception e) {
			output("*** ���� ����� ���� �߻�");
		}
		System.exit(0);
	}

	// �Է� �̺�Ʈ ó��
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == send_btn) {
			String message = input_tp.getText();
			if (message.isEmpty())
				return;
			input_tp.setText("");

			Data data = new Data(Data.CHAT_MESSAGE, username, message);
			try {
				output.writeObject(data);
			} catch (Exception e1) {
				output("*** ���� ����");
			}
		}
	}

	// �����κ����� ���� ���
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
				message = data.getMessage();

				switch (state) {
				case Data.FIRST_CONNECTION:
				case Data.DISCONNECTION:
					output("*** " + message);
					// ������ ��� ����
				//	ArrayList<String> usernames = data.getUsernames();
				//	String[] strnames = usernames.toArray(new String[usernames.size()]);
				//	userList.setListData(strnames);
					break;
				case Data.CHAT_MESSAGE:
					output(name + "> " + message);
					break;
				}

			} catch (Exception e) {
				return;
			}
		}
	}
}
