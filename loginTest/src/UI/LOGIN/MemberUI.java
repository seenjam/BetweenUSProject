package UI.LOGIN;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DAO.MemberDAO;
import Main.MemberMain;
import VO.MemberVO;

public class MemberUI extends JDialog implements ActionListener {
	private JTextField memId_tf; 
	private JTextField memName_tf;
	private JTextField memAddr_tf;
	private JPasswordField memPw_tf;
	private JPasswordField memPwCheck_tf;
	
	private JLabel memPw_lb;
	private JLabel memPwCheck_lb;
	private JLabel memId_lb;
	private JLabel memName_lb;
	private JLabel memAddr_lb; 
	
	private JButton memIdCheck_btn; 	//중복검사 Button
	private JButton memAddrSearch_btn;	//주소검색 Button
	private JButton memInsert_btn;	 	//Member 가입
	private JButton memBack_btn;		//Member 가입 취소
	
	private JLabel explainId_lb;		//아이디 기입 설명 (5자리 이상)
	private JLabel explainPw_lb;		//비밀번호와 비밀번호확인 비교 설명

	private MemberDAO memDao;
	private MemberMain memMain = new MemberMain();

	public MemberUI() {
		memDao = new MemberDAO();
		getContentPane().setBackground(Color.WHITE);
		setSize(500, 500);
		getContentPane().setLayout(null);

		JLabel lblNewLabel_title = new JLabel("회원가입");
		lblNewLabel_title.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 15));
		lblNewLabel_title.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_title.setHorizontalAlignment(JLabel.CENTER);
		lblNewLabel_title.setBounds(152, 25, 145, 52);
		getContentPane().add(lblNewLabel_title);

		memId_lb = new JLabel("\uC544\uC774\uB514");
		memId_lb.setBounds(38, 89, 57, 15);
		getContentPane().add(memId_lb);

		memPw_lb = new JLabel("\uBE44\uBC00\uBC88\uD638");
		memPw_lb.setBounds(28, 141, 57, 15);
		getContentPane().add(memPw_lb);

		memPwCheck_lb = new JLabel("\uBE44\uBC00\uBC88\uD638\uD655\uC778");
		memPwCheck_lb.setBounds(28, 166, 84, 15);
		getContentPane().add(memPwCheck_lb);

		memName_lb = new JLabel("\uC774\uB984");
		memName_lb.setBounds(28, 221, 57, 15);
		getContentPane().add(memName_lb);

		memId_tf = new JTextField();
		memId_tf.setBounds(133, 89, 116, 21);
		memId_tf.setText("");
		getContentPane().add(memId_tf);
		memId_tf.setColumns(10);

		memName_tf = new JTextField();
		memName_tf.setText("");
		memName_tf.setBounds(108, 218, 116, 21);
		getContentPane().add(memName_tf);
		memName_tf.setColumns(10);

		memIdCheck_btn = new JButton("\uC911\uBCF5\uAC80\uC0AC");
		memIdCheck_btn.setBounds(281, 85, 97, 23);
		getContentPane().add(memIdCheck_btn);
		memIdCheck_btn.addActionListener(this);

		memAddr_tf = new JTextField();
		memAddr_tf.setBounds(108, 243, 116, 21);
		memAddr_tf.setText("");
		getContentPane().add(memAddr_tf);
		memAddr_tf.setColumns(10);

		memAddr_lb = new JLabel("\uC8FC\uC18C");
		memAddr_lb.setBounds(28, 246, 57, 15);
		getContentPane().add(memAddr_lb);

		memAddrSearch_btn = new JButton("\uC8FC\uC18C\uAC80\uC0C9");
		memAddrSearch_btn.setBounds(281, 242, 97, 23);
		getContentPane().add(memAddrSearch_btn);
		memAddrSearch_btn.addActionListener(this);

		memInsert_btn = new JButton("\uC644\uB8CC");
		memInsert_btn.setBounds(108, 318, 97, 23);
		getContentPane().add(memInsert_btn);
		memInsert_btn.addActionListener(this);

		memBack_btn = new JButton("\uCDE8\uC18C");
		memBack_btn.setBounds(251, 318, 97, 23);
		getContentPane().add(memBack_btn);
		memBack_btn.addActionListener(this);

		explainId_lb = new JLabel("\uC544\uC774\uB514\uB294 5\uC790\uB9AC \uC774\uC0C1");
		explainId_lb.setFont(new Font("굴림", Font.PLAIN, 11));
		explainId_lb.setForeground(Color.BLACK);
		explainId_lb.setBounds(108, 120, 170, 15);
		getContentPane().add(explainId_lb);

		explainPw_lb = new JLabel("");
		explainPw_lb.setFont(new Font("굴림", Font.PLAIN, 11));
		explainPw_lb.setForeground(Color.RED);
		explainPw_lb.setBounds(95, 193, 202, 15);
		getContentPane().add(explainPw_lb);

		memPw_tf = new JPasswordField();
		memPw_tf.setBounds(133, 135, 145, 21);
		memPw_tf.setText("");
		getContentPane().add(memPw_tf);

		memPwCheck_tf = new JPasswordField();
		memPwCheck_tf.setBounds(133, 158, 148, 23);
		memPwCheck_tf.setText("");
		getContentPane().add(memPwCheck_tf);
		setVisible(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = memId_tf.getText();
		char[] pass1 = memPw_tf.getPassword();
		char[] pass2 = memPwCheck_tf.getPassword();
		String password11 = new String(pass1);
		String password22 = new String(pass2);

		if (e.getSource() == memIdCheck_btn) { // 아이디 중복검사

			if (memId_tf.getText().equals("")) { // 빈칸일때
				explainId_lb.setText("아이디를 입력해주세요.");
			} else {
				if (memId_tf.getText().length() < 5) { // 아이디가 5자리 아래일 때
					explainId_lb.setForeground(Color.RED);
					explainId_lb.setText("5자리 이상 입력해주세요!");
				} else {
					if (memDao.IDcheck(id) == 0) { // IDcheck==true라면
						// id = memId_tf.getText();
						String name = memName_tf.getText();
						String address = memAddr_tf.getText();
						explainId_lb.setForeground(Color.BLACK);
						explainId_lb.setText("사용가능한 아이디입니다.");

						MemberVO vo = new MemberVO();
						/* vo.setMemberID(id); */

					}
					if (memDao.IDcheck(id) == 1) {
						explainId_lb.setForeground(Color.RED);
						explainId_lb.setText("이미 존재하는 아이디 입니다.");
					}
				}

			}

		}

		 //회원가입 완료버튼 클릭 시
		if (e.getSource() == memInsert_btn) {
			if(memId_tf.getText().equals("") || password22.equals("") || password11.equals("")
					|| memName_tf.getText().equals("") || memAddr_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "빈 폼이 있습니다!");
			}else { 
				if(!password11.equals(password22)) {
					explainPw_lb.setForeground(Color.RED);
					explainPw_lb.setText("비밀번호확인이 실패!");
				} else {
					explainPw_lb.setForeground(Color.black);
					explainPw_lb.setText("비밀번호확인 성공");
					
					if (explainId_lb.getText().equals("사용가능한 아이디입니다.")) {
						String name = memName_tf.getText();
						String address = memAddr_tf.getText();

						MemberVO vo = new MemberVO();
						vo.setMemID(id);
						vo.setMemPW(password11);
						vo.setMemName(name);
						vo.setMemAddress(address);
						vo.setMemState("0"); //state 0: 로그오프  , 1: 로그인
						memDao.insertMember(vo);
						JOptionPane.showMessageDialog(null, "회원이 되신 것을 환영합니다!");
						memMain.showLogin();
					} else if(!explainId_lb.getText().equals("사용가능한 아이디입니다.")){
						JOptionPane.showMessageDialog(null, "아이디 중복검사를 해주세요");
					} else {
						JOptionPane.showMessageDialog(null, "아이디를 다시 설정해주세요!");
					}
				}
			}

		//회원가입 취소 버튼 클릭시
		} else if (e.getSource() == memBack_btn) { 

			//취소 -> 다시 로그인 화면으로 전환
			memMain.showLogin();
		}

	}
}
