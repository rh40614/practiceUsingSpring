package edu.storyboard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.storyboard.vo.BoardVO;
import edu.storyboard.vo.SearchVO;

@Repository
public class BoardDAO {

	@Autowired
	SqlSession sqlSession;
	
	private static final String nameSpace = "edu.storyboard.mapper.BoardMapper";
	
	@RequestMapping
	public List<BoardVO> list(SearchVO vo ) {
		return sqlSession.selectList(nameSpace+".list_notice", vo);
	}
	
	
	
}
