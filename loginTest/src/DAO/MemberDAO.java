package DAO;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import VO.FriendVO;
import VO.MemberVO;

public class MemberDAO {
	private static SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	public void insertMember(MemberVO vo) {
		SqlSession session = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			mapper.insertMember(vo);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}
	}

	public int IDcheck(String id) {
		int flag = 0;

		SqlSession session = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			if (mapper.IDcheck(id) == 0) { // null이면(id가 없으면) 2
				flag = 0;
			} else { // null이 아니면(id가 있으면) 1
				flag = 1;
			}
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}
		return flag;

	}

	public String LoginCheck(String id) {
		String pass = null;

		SqlSession session = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);

			pass = mapper.LoginCheck(id);

			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}

		return pass;
	}



	public int searchFriendByNameCount(String name) {
		int friendCount = 0;
		SqlSession session = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);

			friendCount = mapper.searchFriendByNameCount(name);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}

		return friendCount;
	}
	
	public int searchFriendByIDCount(String id) {
		int friendCount = 0;
		SqlSession session = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);

			friendCount = mapper.searchFriendByIDCount(id);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}

		return friendCount;
	}
	
	
	public MemberVO searchFriendByName(String name) {
		SqlSession session = null;
		MemberVO mem = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);

			mem = mapper.searchFriendByName(name);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}
		return mem;

	}
	
	public MemberVO searchFriendByID(String id) {
		SqlSession session = null;
		MemberVO mem = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);

			mem = mapper.searchFriendByID(id);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}
		return mem;
	}

	
	public ArrayList<MemberVO> searchFriendByNameList(String name) {
		ArrayList <MemberVO> memList = null;
		SqlSession session = null;
		
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			memList= mapper.searchFriendByNameList(name);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null) 
				session.close();
		}
		return memList;
	}
	public ArrayList<MemberVO> searchFriendByIDList(String id){
		ArrayList <MemberVO> memList = null;
		SqlSession session = null;
		
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			memList= mapper.searchFriendByIDList(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null) 
				session.close();
		}
		return memList;
	}

	
	/*public MemberVO vovovo(String name) {
		
		retur
	}*/
	public void updateLoginState(String id) {
		SqlSession session = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			mapper.updateLoginState(id);

			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session !=null) {
			session.close();
			}
		}
	}
	
	public MemberVO MyInfo(String id) {			// 로그인 성공한 아이디로 내 정보를 불러옴
		SqlSession session = null;
		MemberVO mem = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);

			mem = mapper.MyInfo(id);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}
		return mem;
	}
	
	public void settingfriends(FriendVO fv) {			//searchFriendsUI에서 새로운 친구들
		SqlSession session = null;
		try {
			System.out.println("settingfriends session");
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			System.out.println(fv);
			mapper.settingfriends(fv);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}
	}
	
	public void settingBetweenFriends(FriendVO fv) {			//searchFriendsUI에서 새로운 친구들
		SqlSession session = null;
		try {
			System.out.println("settingBetweenFriends session");
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			mapper.settingBetweenFriends(fv);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}
	}
	
	public ArrayList<String> findingMyFriendsID(String myID)   {					//FriendUI에서 원래 저장되어있던 친구목록 부르는거
		SqlSession session = null;
		ArrayList<String> friendsID =new ArrayList<>();
		//MemberVO mem = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);

			friendsID = mapper.findingMyFriendsID(myID);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}
		return friendsID;

	}
	
	public MemberVO findingMyFriend(String id) {
		SqlSession session = null;
		//String friendsID =null;
		MemberVO f = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);

			f = mapper.findingMyFriend(id);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}
		return f;
	}
	
	public int friendsCount(String myID) {
		int friendCount = 0;
		SqlSession session = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);

			friendCount = mapper.friendsCount(myID);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}

		return friendCount;
	}
	
	public ArrayList<String> friendsCheck(String myID) {
		SqlSession session = null;
		ArrayList<String> friendsID =new ArrayList<>();
		//MemberVO mem = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);

			friendsID = mapper.friendsCheck(myID);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}
		return friendsID;
	}
	
}
