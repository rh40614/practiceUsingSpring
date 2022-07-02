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

	
	@ResponseBody //ds가 반환값을 가로채서 viewResolver로 보내지 안도록 반환값을 호출한곳이 가져가도록한다. 
	@RequestMapping(value="/userInfo.do", produces = "application/json;charset=utf-8")
	public UserVO userInfo(HttpSession session, HttpServletRequest request) {
		
		session = request.getSession();
		UserVO login = (UserVO)session.getAttribute("login");
		
		UserVO vo = userService.selectByMypage(login.getMidx());
		
		//ajax
//		String result ="유저정보=> id: "+vo.getMidx()
//					  + ", password: "+vo.getPassword()
//					  + ", name: "+vo.getUserName()
//					  + ", midx: "+vo.getMidx();
//		return result;	
		//result를 ds가 받아서 화면을 받도록 시키는데, 이 값은 ajax 데이터 이므로 이 메서드를 호출한 곳으로 보내야하는 결과이다. 
		//ds가 값을 가로채지 못하게 하기 위해 @ResponseBody태그를 달아서 응답한바디(?)가 가져갈 수있도록 한다.
		
		//jackson 사용
		return vo;
		
		
		
		
		
	}
	
	@ResponseBody						//문자열을 반환하고 있음으로 json이 아니라 text로  반환값을 받아준다.
	@RequestMapping(value="goText.do", produces = "application/text;charset=utf-8" )
	public String goText(String text) { 	//ajax의 타입을 매개변수로가지고 간다.  data: "text="+$("#t1").val(),
		return text+ "_ajax";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="boardList.do", produces = "application/json;charset=utf-8")
	public List<BoardVO> boardList(SearchVO vo){
		return boardService.list(vo);	//화면에서 입력받은 검색어를 매개변수로 넘겨서 메서드를 실행하도록 명령
	}	//잭슨 라이브러리가 알아서 json으로 만들어준다.
	
	
	
	@ResponseBody
	@RequestMapping(value="boardView.do", produces = "application/json;charset=utf-8")
	public BoardVO boardView(int bidx) {
		return boardService.selectOne(bidx);
	}
	
	
	
	
	
	
	
	
	
	
	
}
