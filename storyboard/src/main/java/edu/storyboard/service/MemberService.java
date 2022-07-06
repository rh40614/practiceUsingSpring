package edu.storyboard.service;

import edu.storyboard.vo.MemberVO;

public interface MemberService {
	
	//회원가입
	public int insertMember(MemberVO vo);
	//로그인 
	public MemberVO login(MemberVO vo);
	//아이디 중복확인 
	public int idCheck(MemberVO vo);
}
