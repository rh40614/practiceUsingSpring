package edu.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.board.vo.BoardVO;
import edu.board.vo.SearchVO;

@Repository	//Db ��� �ܺο� �����ϴ� ��ü ������ repository��� ǥ�����ش�. �ܺο� �ִ� �ڿ��� ����
//�̷��� ��Ű���� ���� ����� ioc ��Ʈ�ѷ��� �����ȿ� �����Ƿ� servlet-context.xml�� component-scan�� �߰��ؼ� ioc�����̳��� ������ �� �� �ֵ��� �Ѵ�. 
public class BoardDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	
	private static final String namespace = "edu.board.mapper.boardMapper";
	
	//selectList�� ȣ���ؼ� ������ �Ѱ��Ѱ� list���·� ��ȯ�Ѵ�.
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
