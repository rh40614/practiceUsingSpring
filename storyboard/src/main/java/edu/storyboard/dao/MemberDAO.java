package edu.storyboard.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import edu.storyboard.vo.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	String nameSpace = "edu.storyboard.mapper.MemberMapper";
	
	//ȸ������ 
	public int insertMember(MemberVO vo) {
		int result = sqlSession.insert(nameSpace+".insertMember", vo);
		
		return result;
	}
	
	//�α��� 
	public MemberVO selectByLogin(MemberVO vo){
		return sqlSession.selectOne(nameSpace+".selectByLogin",vo);
	}
	
	//���̵� �ߺ��˻�
	public int idCheck(MemberVO vo) {
		return sqlSession.selectOne(nameSpace+".idCheck", vo);
	}

}
