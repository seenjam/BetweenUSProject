package UI.LOGIN;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DAO.MemberDAO;
import Main.MemberMain;
import VO.MemberVO;

public class LoginUI extends JFrame implements ActionListener{
	private JTextField memId_tf;    		//아이디를 입력하는 TextField
	private JPasswordField memPw_pf;		//Password를 입력하는 passwordField
	private JButton login_btn; 				//로그인 Button
	private JButton newMember_btn;      	//회원가입 Button
	private JLabel memId_lb;				//아이디 label
	private JLabel memPw_lb;				//비밀번호 label
	private JLabel blankCheck_lb;	    	//빈칸여부 판단 label
	
	private MemberUI memberUI = new MemberUI();
	private MemberDAO memberDao = new MemberDAO();
	private MemberMain memberMain = new MemberMain();
	

	
	private MemberVO myInfo= null; //내정보를 담고 있는 변수
	
	public LoginUI() {
		setSize (1350,800);
		getContentPane().setBackground(SystemColor.window);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_title = new JLabel("LOGIN");
		lblNewLabel_title.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_title.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 30));
		lblNewLabel_title.setBounds(574, 119, 217, 35);
		getContentPane().add(lblNewLabel_title);
		
		
		memId_tf = new JTextField();
		memId_tf.setBounds(602, 178, 158, 21);
		getContentPane().add(memId_tf);
		memId_tf.setColumns(10);
		
		
		memId_lb = new JLabel("UserID");
		memId_lb.setHorizontalAlignment(SwingConstants.CENTER);
		memId_lb.setBounds(477, 181, 113, 15);
		getContentPane().add(memId_lb);
		
		
		
		memPw_pf = new JPasswordField();
		memPw_pf.setBounds(602, 209, 158, 21);
		getContentPane().add(memPw_pf);
		
		
		memPw_lb = new JLabel("Password");
		memPw_lb.setHorizontalAlignment(SwingConstants.CENTER);
		memPw_lb.setBounds(477, 212, 113, 15);
		getContentPane().add(memPw_lb);
		
		
		
		login_btn = new JButton("로그인");
		login_btn.setBounds(574, 256, 81, 35);
		getContentPane().add(login_btn);
		//로그인 버튼에 ActionListener 추가
		login_btn.addActionListener(this);  
		
		
		
		newMember_btn = new JButton("회원가입");
		newMember_btn.setBounds(678, 256, 103, 35);
		getContentPane().add(newMember_btn);
		//회원가입 버튼에 ActionListener 추가
		newMember_btn.addActionListener(this);
		
		
		
		blankCheck_lb = new JLabel(" ");
		blankCheck_lb.setHorizontalAlignment(SwingConstants.CENTER);
		blankCheck_lb.setFont(new Font("굴림", Font.PLAIN, 11));
		blankCheck_lb.setBounds(259, 222, 164, 15);
		getContentPane().add(blankCheck_lb);
		
		

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = memId_tf.getText();
		char[] pass = memPw_pf.getPassword();
		String password = new String(pass);
		
		//로그인 버튼 클릭시
		if(e.getSource() == login_btn) {
			blankCheck_lb.setForeground(Color.RED);
			if(id.equals("")) {
				blankCheck_lb.setText("Please enter your id");
			}else if(password.equals("")){
				blankCheck_lb.setText("Please enter your password");
			}else {
				if(memberDao.IDcheck(id) == 0 ) {
					JOptionPane.showMessageDialog(null, "로그인실패!");
				} else {
					if(!memberDao.LoginCheck(id).equals(password)) {
						JOptionPane.showMessageDialog(null, "로그인실패!");
					}else {
						memberMain.loginSuccess(memberDao.MyInfo(id));
						memberMain.updateLoginState(id);
	
						setVisible(false);
					}
				}			
			}
		}
		
		
		//회원가입버튼 클릭시
		if(e.getSource() == newMember_btn) {
			
			memberMain.showSignUp();
		}
		
	}
}
