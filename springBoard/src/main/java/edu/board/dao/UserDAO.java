package edu.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.board.vo.UserVO;

@Repository	//�ܺ��ڿ���� ������ �Ǵ� Ŭ�������� �ִ� ������̼�
public class UserDAO {
	
	//root-context�� mybatis bean ����� �а� �ڵ����� �ޱ�
	//dbconn�� ����(root-context�� bean���� ����� �ξ���)
	//�淮ȭ ��Ŵ �̱��� ������ �ϳ��� ���� ���������ؼ���
	@Autowired		
	SqlSession sqlSession;	//root-context���� bean���� �����Ǿ��ִ�.	//userDao�� ��üȭ�Ǵ� ������ �굵
	
	
	//�ܺ��� db�� �����͸� �Է��ϴ� �޼���. �Է��ϴ� ������ UserVo�� �Ű������� �����ͼ� ����
	public int insert(UserVO vo) {	//��Ʈ�ѷ�- service - DAO
		
		// sqlSession.insert("",); ���� insert�� mybatis�� insert����. (db�� insert�����̶�� �ϴ� ����)
		// Mapper�� ������ �� �ּ�.(userMapper�� �������ִ�.)�޼���� �ۼ�  //�� ���۾ȿ� �ִ� �޼��带 �����ϰ� ������ ���� �����͸� �ѱ�ڴ�.
		// �����ʹ� ���ۿ��� #{�ʵ��} ���� ����.
		int result = sqlSession.insert("edu.board.mapper.userMapper.insert", vo );	
		
		return result;
	}

	
	//�α��� ó��
	public UserVO selectByLogin(UserVO vo) {
		return sqlSession.selectOne("edu.board.mapper.userMapper.selectByLogin", vo); 	//�ѰǸ� ��ȯ�ϰڴ�. �Ѱ��϶��� ���� ������ ��ȸ�ؼ� �ϳ��� ��ȯ�ȵ�. �ϳ��� ��ȸ�ϴ� �������� ��밡��
	}
	
	//���������� (midx�� �ش��ϴ� ȸ���� ������ ��ȸ)
	public UserVO selectByMypage(int midx) {
		
		return sqlSession.selectOne("edu.board.mapper.userMapper.selectByMypage", midx);
	}
	
	//ȸ������ ���� 
	public int modify(UserVO vo) {
		return sqlSession.update("edu.board.mapper.userMapper.modify", vo);
	} 
	
}
