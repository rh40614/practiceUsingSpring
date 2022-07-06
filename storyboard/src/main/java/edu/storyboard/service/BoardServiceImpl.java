package edu.storyboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.storyboard.dao.BoardDAO;
import edu.storyboard.vo.BoardVO;
import edu.storyboard.vo.SearchVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO boardDAO;

	@Override
	public List<BoardVO> list_notice(SearchVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> list(SearchVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
