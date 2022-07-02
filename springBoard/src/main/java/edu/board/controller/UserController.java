package edu.board.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.board.service.UserService;
import edu.board.vo.UserVO;



//어떤 uri를 타면 이 컨트롤러로 들어오는지 지정
@RequestMapping(value="/user")
@Controller
public class UserController {	//화면으로 데이터를가지고 오고 데이터를 넣는것만 컨트롤러에서 한다. 
	
	@Autowired	//ioc 컨트롤러에게 implements 객체 구현해서 필드에 담아
	UserService userService;
	

	@RequestMapping(value = "/resister.do", method = RequestMethod.GET) 
	public String resister(HttpServletRequest request, HttpSession session) {
		HashMap<String,String> test = new HashMap<String,String>();
		
		test.put("name","kim");
		test.put("age","20");
		
		session = request.getSession();
		
		session.setAttribute("user", test);
		
		return "user/resister";
	}
		
	//오버로딩해서 활용. 가상 주소가 같지만 전송방식 달라서 다시 사용가능	
	@RequestMapping(value = "/resister.do", method = RequestMethod.POST) //하나의 가상메서드는 분리해서 두가지 방식으로 쓸 수있다.
	public String resister(UserVO vo, Model model) {
		/*
		가상주소를 재활용하는것 //하나의 메서드를 분리해서 두가지 방식으로 활용
		수정하는 페이지의 경우 
		수정을 누르면 수정페이지로 이동.(GET)
		수정완료를 누르면 데이터 처리를 하고 이동하므로.(POST)
		*/
		/*
			파라미터를 메서드로 전달 받는 방법은 매개변수의 명을 파라미터 키와 맞추는 방법과
			매개변수 VO의 필드명을 맞추는 방법이 있다.
		*/
			
		/*
		 * model.addAttribute("userName", userName); model.addAttribute("userAge",
		 * userAge); model.addAttribute("userAdr", userAdr);
		 * model.addAttribute("userPhone", userPhone);
		 */
		
		model.addAttribute("vo", vo);
		
	return "user/info";	//ds가 이 값을 받아서 view resolver한테 주고 fix 붙혀서 화면을 찾는다. 
	
	}
	
	
	
	
	//회원가입 페이지 이동
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	//회원가입
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String join(UserVO vo, Model model) {
		//UserService userService =new userServiceImpl();	
		//위처럼 new로 만들면 회원가입할때마다 메모리가 할당이 되어서 메모리를 너무 잡아먹어서 싱글톤으로 service를 컨트롤러에 필드로 두기
		//화면에서 받아온 데이터 service로 보내기
		//serviceImpl에 메서드 만들어서 
		int result = userService.insertUser(vo);	//파란색은 필드라는뜻 
		
		return "redirect:/";	// redirect할때는 redirect:url경로값을 적어주면된다. 
	}
	
	//로그인경로로 이동
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	
	
	//로그인하고 메인페이지로 이동
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, HttpServletRequest request, HttpSession session) {	
		
		//서비스 구현객체에서 Dao를 통해가지고 온 (mybatis를 이용해서 ) 데이터를 서비스(화면)에서 어떻게할지 정하고 컨트롤러에서 호출해서 그 결과를~
		UserVO user = userService.selectByLogin(vo);
		
		//로그인 성공
		if(user !=null) {
			
			session =request.getSession();
			//새로운 userVO 만들어서 session을 담아서 사용한다.
			UserVO login = new UserVO();	//세션에는 비밀번호 전화번호 개인정보는 넣으면 안된다!
			login.setMidx(user.getMidx());
			login.setId(user.getId());
			login.setUserName(user.getUserName());
			
			session.setAttribute("login", login);
			
			return "redirect:/";
		}else {
			return "redirect:/user/login.do";
		}
		
		
		
	}
	
	//로그아웃. 따로 메서드 쓸것은 없기 때문에 컨트롤러에서 끝
	@RequestMapping(value="/logout.do")
	public String logout(HttpServletRequest request, HttpSession session) {
		
		session = request.getSession();
		session.invalidate();
				
		return "redirect:/";
	}
	
	
	
	//마이페이지
	@RequestMapping(value="/mypage.do")
	public String mypage(HttpServletRequest request, HttpSession session, Model model) {
		
		//세션형성
		session = request.getSession();
		//로그인 객체 꺼내오기
		UserVO login = (UserVO)session.getAttribute("login"); //세션값은 오브젝트타입이니까 형변환해주기
		
		//해당하는 회원의 상세정보가지고 오기  
		UserVO vo = userService.selectByMypage(login.getMidx());
		
		model.addAttribute("vo", vo);
		
		
		return "user/mypage";
	}
	
	
	
	//회원정보 수정페이지
	@RequestMapping(value="/modify.do", method=RequestMethod.GET)
	public String modify(int midx, Model model) {
		
		//midx에 해당하는 회원정보를 넘기는 메서드
		UserVO vo = userService.selectByMypage(midx);
		
		model.addAttribute("vo", vo);
		
		return "user/modify";
	}
	
	
	//수정정보 저장
	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
	public String modify(UserVO vo) {
		int result =userService.modify(vo);
		
		if(result == 1) {
			System.out.println("회원정보 수정 성공");
		}else {
			System.out.println("회원정보 수정 실패");
		}
		
		return "redirect:/user/mypage.do";
	}
	
	
			
	
}
