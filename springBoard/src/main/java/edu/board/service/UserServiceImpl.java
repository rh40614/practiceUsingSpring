package edu.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.board.dao.UserDAO;
import edu.board.vo.UserVO;

@Service	//ioc 컨테이너에게 여기서 Service에 대한것을 처리하겠다.
public class UserServiceImpl implements UserService{	//비지니스 로직을 담는 곳이 service
	
	@Autowired
	UserDAO userDao;
	
	@Override	//회원가입이지만 화면에서하는것은 데이터를 넣는것
	public int insertUser(UserVO vo) {	
		// DAO 생성해서 메서드를 불러와야하는데 개발자가 생성하는것이 아니라 ioc컨테이너한테 시킴.
		// 필드에 자동으로 의존주입해서 생성하고 사용하기.
		// db에 그냥담는거라 데이터 처리하는  내용은 따로 없음.
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
