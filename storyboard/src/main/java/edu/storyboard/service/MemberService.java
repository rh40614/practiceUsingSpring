package edu.storyboard.service;

import edu.storyboard.vo.MemberVO;

public interface MemberService {
	
	//ȸ������
	public int insertMember(MemberVO vo);
	//�α��� 
	public MemberVO login(MemberVO vo);
	//���̵� �ߺ�Ȯ�� 
	public int idCheck(MemberVO vo);
}
