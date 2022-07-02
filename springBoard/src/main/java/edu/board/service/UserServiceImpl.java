package edu.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.board.dao.UserDAO;
import edu.board.vo.UserVO;

@Service	//ioc �����̳ʿ��� ���⼭ Service�� ���Ѱ��� ó���ϰڴ�.
public class UserServiceImpl implements UserService{	//�����Ͻ� ������ ��� ���� service
	
	@Autowired
	UserDAO userDao;
	
	@Override	//ȸ������������ ȭ�鿡���ϴ°��� �����͸� �ִ°�
	public int insertUser(UserVO vo) {	
		// DAO �����ؼ� �޼��带 �ҷ��;��ϴµ� �����ڰ� �����ϴ°��� �ƴ϶� ioc�����̳����� ��Ŵ.
		// �ʵ忡 �ڵ����� ���������ؼ� �����ϰ� ����ϱ�.
		// db�� �׳ɴ�°Ŷ� ������ ó���ϴ�  ������ ���� ����.
		int result = userDao.insert(vo);
		return result;
	}

	@Override
	public UserVO selectByLogin(UserVO vo) {
		return userDao.selectByLogin(vo);
	}

	@Override
	public UserVO selectByMypage(int midx) {
		return userDao.selectByMypage(midx);
	}

	@Override
	public int modify(UserVO vo) {
		return userDao.modify(vo);
	}

	
	

}
