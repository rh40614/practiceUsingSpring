package edu.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.board.service.BoardService;
import edu.board.vo.BoardVO;
import edu.board.vo.SearchVO;
import edu.board.vo.UserVO;

@RequestMapping(value="/board")
@Controller
public class boardController {	//브레이크 포인트 설정하고 실행하면 거기까지만 실행이되고, f6 눌러서 들어온 데이터도 확인가능 포인트 기준으로 한줄씩 실행해서 확인 할 수있다. 
	
	@Autowired
	BoardService boardService;
	
	//Ds가 접근해서 가져가기때문에 접근제어자는 항상 public
	@RequestMapping(value="/list.do")
	public String list(Model model, SearchVO searchVO) {	//검색기능 사용할 수 있도록 searchVO 넘기기 
		//searchVO는 객체가될때 기본값이 null이므로 VO자체가 null인지 확인하는것은 의미가 없다.
		//리스트 불러오기
		List<BoardVO> list = boardService.list(searchVO);
		//화면단으로 넘기기
		model.addAttribute("list", list);
		//게시물을 검색한 후에 검색어가 계속 있도록 유지 시키기 위해 화면단으로 넘기기
		model.addAttribute("searchVO", searchVO);
		
		return "board/list";
	}

	//게시글 작성페이지로 이동
	@RequestMapping(value="/write.do",  method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	//게시글 작성 실행
	@RequestMapping(value="/write.do",  method = RequestMethod.POST)
	public void write(BoardVO vo, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException {
		// HttpServletResponse response를 사용하면 컨트롤러에서 화면에 alert 가능
		
		//midx가져와야하니까 session형성
		session = request.getSession();
		
		//boardVO vo 에 midx를 담아서 mapper까지 보내는 것
		UserVO login =(UserVO)session.getAttribute("login");
		vo.setMidx(login.getMidx());
		
		//System.out.println("midx: " + vo.getMidx());
		
		//게시글 작성
		int result = boardService.insert(vo);
		
		//PrintWriter: 화면에서 응답을 하는것이므로 response 객체를 사용하여 controller 창에서  화면단에 alert를 띄울 수 있다. 
		//한글임으로 utf 설정해주기
		//지금응답하는 곳에 html 을써라 
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		if(result == 1) {
			System.out.println("게시글 등록 성공");
			//html형식으로 작성하는것이기 때문에 script 태그부터 작성하여 alert를 띄울 수 있도록 한다. 
			//response를 이용해서 script를 띄우면 redirect가 먹지않기 때문에 script를 이용해서 화면을 이동시켜주어야한다. 
			pw.append("<script>alert('게시글이 성공적으로 작성되었습니다.');location.href='view.do?bidx="+vo.getBidx()+"';</script>");
		}else {
			System.out.println("게시글 등록 실패");		
			pw.append("<script>alert('등록되지않았습니다.');location.href='list.do'</script>");	//얘 자체가 화면을 하나띄우는거기때문에 이동을 script에 작성
			pw.flush();//
		}
		
		/* return "redirect:/board/list.do"; */	//return 안하고 if 절에서 alert띄우고 바로 이동시킴
	}
	
	
	//게시글 상세 보기
	@RequestMapping(value="/view.do")
	public String view(int bidx, Model model) {
		//게시물하나 가져오기(bidx를 매개변수로 넘겨서 해당하는 게시물을 가지고 온다.)
		BoardVO vo = boardService.selectOne(bidx);
		//화면단으로 데이터 가져가기
		model.addAttribute("vo", vo);
		//포워딩
		return "board/view";
		
	}
	
	
	
	
}
