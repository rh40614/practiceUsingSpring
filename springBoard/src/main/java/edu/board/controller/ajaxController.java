package edu.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.board.service.BoardService;
import edu.board.service.UserService;
import edu.board.vo.BoardVO;
import edu.board.vo.SearchVO;
import edu.board.vo.UserVO;

@RequestMapping(value="/ajax")
@Controller
public class ajaxController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BoardService boardService;
	
		
	@RequestMapping(value="/sample.do")
	public String sample() {
		return "ajax/sample";
	}

	
	@ResponseBody //ds�� ��ȯ���� ����ä�� viewResolver�� ������ �ȵ��� ��ȯ���� ȣ���Ѱ��� �����������Ѵ�. 
	@RequestMapping(value="/userInfo.do", produces = "application/json;charset=utf-8")
	public UserVO userInfo(HttpSession session, HttpServletRequest request) {
		
		session = request.getSession();
		UserVO login = (UserVO)session.getAttribute("login");
		
		UserVO vo = userService.selectByMypage(login.getMidx());
		
		//ajax
//		String result ="��������=> id: "+vo.getMidx()
//					  + ", password: "+vo.getPassword()
//					  + ", name: "+vo.getUserName()
//					  + ", midx: "+vo.getMidx();
//		return result;	
		//result�� ds�� �޾Ƽ� ȭ���� �޵��� ��Ű�µ�, �� ���� ajax ������ �̹Ƿ� �� �޼��带 ȣ���� ������ �������ϴ� ����̴�. 
		//ds�� ���� ����ä�� ���ϰ� �ϱ� ���� @ResponseBody�±׸� �޾Ƽ� �����ѹٵ�(?)�� ������ ���ֵ��� �Ѵ�.
		
		//jackson ���
		return vo;
		
		
		
		
		
	}
	
	@ResponseBody						//���ڿ��� ��ȯ�ϰ� �������� json�� �ƴ϶� text��  ��ȯ���� �޾��ش�.
	@RequestMapping(value="goText.do", produces = "application/text;charset=utf-8" )
	public String goText(String text) { 	//ajax�� Ÿ���� �Ű������ΰ����� ����.  data: "text="+$("#t1").val(),
		return text+ "_ajax";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="boardList.do", produces = "application/json;charset=utf-8")
	public List<BoardVO> boardList(SearchVO vo){
		return boardService.list(vo);	//ȭ�鿡�� �Է¹��� �˻�� �Ű������� �Ѱܼ� �޼��带 �����ϵ��� ���
	}	//�轼 ���̺귯���� �˾Ƽ� json���� ������ش�.
	
	
	
	@ResponseBody
	@RequestMapping(value="boardView.do", produces = "application/json;charset=utf-8")
	public BoardVO boardView(int bidx) {
		return boardService.selectOne(bidx);
	}
	
	
	
	
	
	
	
	
	
	
	
}
