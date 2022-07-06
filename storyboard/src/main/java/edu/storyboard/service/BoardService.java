package edu.storyboard.service;

import java.util.List;

import edu.storyboard.vo.BoardVO;
import edu.storyboard.vo.SearchVO;

public interface BoardService {
	
	public List<BoardVO> list_notice(SearchVO vo);
	public List<BoardVO> list(SearchVO vo);
}
