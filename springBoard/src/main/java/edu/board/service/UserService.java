package edu.board.service;

import edu.board.vo.UserVO;

//interface는 어차피 인스턴스로 생성이 될 수 없으니까 ioc 컨테이너가 읽지 않아도 된다. 
public interface UserService {
	
	//회원가입이지만 화면에서하는것은 데이터를 넣는것이므로 그것에 맞추어 이름짓기
	int insertUser(UserVO vo);
	//로그인 메서드
	UserVO selectByLogin(UserVO vo);
	//마이페이지 
	UserVO selectByMypage(int midx);
	//회원정보 수정
	int modify (UserVO vo);
	
}
