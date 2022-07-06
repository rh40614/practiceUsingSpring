package edu.storyboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.storyboard.dao.MemberDAO;
import edu.storyboard.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memberDAO;

	@Override
	public int insertMember(MemberVO vo) {
		return memberDAO.insertMember(vo);
	}

	@Override
	public MemberVO login(MemberVO vo) {
		return memberDAO.selectByLogin(vo);
	}

	@Override
	public int idCheck(MemberVO vo) {
		return memberDAO.idCheck(vo);
	}

}
