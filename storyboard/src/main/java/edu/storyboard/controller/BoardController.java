package edu.storyboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.storyboard.vo.BoardVO;
import edu.storyboard.vo.SearchVO;
import edu.storyboard.service.BoardService;


@RequestMapping(value="/board")
@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	
	
	//��������
	@RequestMapping(value="/notice.do")
	public String list(Model model, SearchVO searchVO) {	//�˻���� ����� �� �ֵ��� searchVO �ѱ�� 	
		
		List<BoardVO> list = boardService.list_notice(searchVO);
		model.addAttribute("list", list);
		model.addAttribute("searchVO", searchVO);
		
		return "board/notice";
	}
	
	
	@RequestMapping(value="/write.do")
	public String write() {
		return "board/write";
	}
	
}
