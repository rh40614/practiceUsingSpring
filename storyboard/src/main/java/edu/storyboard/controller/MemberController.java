package edu.storyboard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.storyboard.service.MemberService;
import edu.storyboard.vo.MemberVO;


@RequestMapping(value="/member")
@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/join.do" , method=RequestMethod.GET)
	public String insertMember() {
		return"member/join";
	}
	
	
	
	//회원가입
	@RequestMapping(value="/join.do" , method=RequestMethod.POST)
	public void insertMember(MemberVO vo, HttpServletResponse response) throws IOException {
		
		//화면단 alert하고 바로 이동
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		int result = memberService.insertMember(vo);
		if(result == 1) {
			System.out.println("회원가입 성공");
			pw.append("<script>alert('회원가입을 축하합니다.');location.href='../'</script>;");
		}else {
			System.out.println("회원가입 실패");
			pw.append("<script>alert('회원가입에 실패하였습니다. 다시 가입해주십시오');location.href='join.do'</script>;");
			pw.flush();	//버퍼의 데이터 지우기
		}	
	}
	
	@RequestMapping(value="/login.do" , method=RequestMethod.GET)
	public String login() {
		return"member/login";
	}
	
	//로그인
	@RequestMapping(value="/login.do" , method=RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest request, HttpSession session) {
		
		//로그인한 회원의 모든정보
		MemberVO member = memberService.login(vo);
		
		if(member != null) {
			System.out.println("로그인 성공");
			session = request.getSession();
			
			//새로운 vo 만들어서 sessiond에 가져온 정보중 필요한 것만 담아서 사용하기
			MemberVO login = new MemberVO();
			login.setMidx(member.getMidx());
			login.setMemberName(member.getMemberName());
			login.setMemberId(member.getMemberId());
			
			session.setAttribute("login", login);
//로그인 이후 공지사항 목록페이지로 이동			
			return "redirect:/";									
		}else {
			return"redirect:/member/login.do";
		}
		
	}
	
	
	//로그아웃
	@RequestMapping(value="/logout.do")
	public void logout(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
		
		session = request.getSession();
		session.invalidate();
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		pw.append("<script>alert('이용해주셔서 감사합니다.');location.href='../'</script>;");
		/* return "redirect:/"; */
	}
	
	
	//아이디 중복검사
	@ResponseBody
	@RequestMapping(value="/idCheck.do", produces = "application/json;charset=utf-8")
	public Map<Object, Object> idcheck(MemberVO vo) {
	        
	Map<Object, Object> map = new HashMap<Object, Object>();
	 
	//db에 있는 데이터와 비교하여 아이디가 있다면 1을 반환하고 없으면 0을 반환하기
	int count = memberService.idCheck(vo);
	map.put("cnt", count);
	
	System.out.println("count: "+ count);

	return map;
	}
	
	
	
	
	
	
	
	
}
