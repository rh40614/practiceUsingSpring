package edu.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.board.vo.UserVO;

@Repository	//외부자원들과 연결이 되는 클래스에게 주는 어노테이션
public class UserDAO {
	
	//root-context에 mybatis bean 만들어 둔거 자동주입 받기
	//dbconn의 역할(root-context에 bean으로 만들어 두었음)
	//경량화 시킴 싱글톤 구조로 하나만 만들어서 의존주입해서씀
	@Autowired		
	SqlSession sqlSession;	//root-context에서 bean으로 설정되어있다.	//userDao가 객체화되는 시점에 얘도
	
	
	//외부의 db로 데이터를 입력하는 메서드. 입력하는 값들은 UserVo를 매개변수로 가져와서 설정
	public int insert(UserVO vo) {	//컨트롤러- service - DAO
		
		// sqlSession.insert("",); 에서 insert는 mybatis의 insert구문. (db의 insert구문이라고 일단 이해)
		// Mapper에 설정해 둔 주소.(userMapper에 쓰여져있다.)메서드로 작성  //이 맵퍼안에 있는 메서드를 실행하고 다음과 같은 데이터를 넘기겠다.
		// 데이터는 맵퍼에서 #{필드명} 으로 쓴다.
		int result = sqlSession.insert("edu.board.mapper.userMapper.insert", vo );	
		
		return result;
	}

	
	//로그인 처리
	public UserVO selectByLogin(UserVO vo) {
		return sqlSession.selectOne("edu.board.mapper.userMapper.selectByLogin", vo); 	//한건만 반환하겠다. 한건일때만 쓰기 여러건 조회해서 하나만 반환안됨. 하나만 조회하는 쿼리에만 사용가능
	}
	
	//마이페이지 (midx에 해당하는 회원의 정보를 조회)
	public UserVO selectByMypage(int midx) {
		
		return sqlSession.selectOne("edu.board.mapper.userMapper.selectByMypage", midx);
	}
	
	//회원정보 수정 
	public int modify(UserVO vo) {
		return sqlSession.update("edu.board.mapper.userMapper.modify", vo);
	} 
	
}
