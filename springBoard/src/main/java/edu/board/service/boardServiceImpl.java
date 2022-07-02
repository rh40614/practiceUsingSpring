package edu.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.board.dao.BoardDAO;
import edu.board.vo.BoardVO;
import edu.board.vo.SearchVO;

@Service//�������̽��� ������ü��� ���� ǥ��
public class boardServiceImpl implements BoardService{

	@Autowired
	BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> list(SearchVO searchVO) {
		return boardDAO.selectAll(searchVO);
	}

	@Override
	public int insert(BoardVO vo) {
		
		return boardDAO.insert(vo);
	}

	@Override
	public BoardVO selectOne(int bidx) {
		
		return boardDAO.selectOne(bidx);
	}	

}
