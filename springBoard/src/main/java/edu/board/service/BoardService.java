package edu.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.board.vo.BoardVO;
import edu.board.vo.SearchVO;

public interface BoardService {

	public List<BoardVO> list(SearchVO searchVO);
	public int insert(BoardVO vo);
	public BoardVO selectOne(int bidx);
	
}
