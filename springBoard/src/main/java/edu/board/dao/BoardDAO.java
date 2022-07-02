package edu.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.board.vo.BoardVO;
import edu.board.vo.SearchVO;

@Repository	//Db 라는 외부에 접근하는 객체 임으로 repository라고 표시해준다. 외부에 있는 자원과 연결
//이렇게 패키지를 새로 만들면 ioc 컨트롤러의 범위안에 없으므로 servlet-context.xml에 component-scan을 추가해서 ioc컨테이너의 하위에 들어갈 수 있도록 한다. 
public class BoardDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	
	private static final String namespace = "edu.board.mapper.boardMapper";
	
	//selectList로 호출해서 보내면 한건한건 list형태로 반환한다.
	public List<BoardVO> selectAll(SearchVO searchVO){
		return sqlSession.selectList(namespace+".selectAll", searchVO);
	}
	
	public int insert(BoardVO vo) {
		return sqlSession.insert(namespace+".insert", vo);	
	}
	
	public BoardVO selectOne(int bidx) {
		return sqlSession.selectOne(namespace+".selectOne", bidx);
		
	}
	
	
	
	
}
