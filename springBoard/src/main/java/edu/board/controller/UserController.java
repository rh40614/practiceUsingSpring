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



//� uri�� Ÿ�� �� ��Ʈ�ѷ��� �������� ����
@RequestMapping(value="/user")
@Controller
public class UserController {	//ȭ������ �����͸������� ���� �����͸� �ִ°͸� ��Ʈ�ѷ����� �Ѵ�. 
	
	@Autowired	//ioc ��Ʈ�ѷ����� implements ��ü �����ؼ� �ʵ忡 ���
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
		
	//�����ε��ؼ� Ȱ��. ���� �ּҰ� ������ ���۹�� �޶� �ٽ� ��밡��	
	@RequestMapping(value = "/resister.do", method = RequestMethod.POST) //�ϳ��� ����޼���� �и��ؼ� �ΰ��� ������� �� ���ִ�.
	public String resister(UserVO vo, Model model) {
		/*
		�����ּҸ� ��Ȱ���ϴ°� //�ϳ��� �޼��带 �и��ؼ� �ΰ��� ������� Ȱ��
		�����ϴ� �������� ��� 
		������ ������ ������������ �̵�.(GET)
		�����ϷḦ ������ ������ ó���� �ϰ� �̵��ϹǷ�.(POST)
		*/
		/*
			�Ķ���͸� �޼���� ���� �޴� ����� �Ű������� ���� �Ķ���� Ű�� ���ߴ� �����
			�Ű����� VO�� �ʵ���� ���ߴ� ����� �ִ�.
		*/
			
		/*
		 * model.addAttribute("userName", userName); model.addAttribute("userAge",
		 * userAge); model.addAttribute("userAdr", userAdr);
		 * model.addAttribute("userPhone", userPhone);
		 */
		
		model.addAttribute("vo", vo);
		
	return "user/info";	//ds�� �� ���� �޾Ƽ� view resolver���� �ְ� fix ������ ȭ���� ã�´�. 
	
	}
	
	
	
	
	//ȸ������ ������ �̵�
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	//ȸ������
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String join(UserVO vo, Model model) {
		//UserService userService =new userServiceImpl();	
		//��ó�� new�� ����� ȸ�������Ҷ����� �޸𸮰� �Ҵ��� �Ǿ �޸𸮸� �ʹ� ��ƸԾ �̱������� service�� ��Ʈ�ѷ��� �ʵ�� �α�
		//ȭ�鿡�� �޾ƿ� ������ service�� ������
		//serviceImpl�� �޼��� ���� 
		int result = userService.insertUser(vo);	//�Ķ����� �ʵ��¶� 
		
		return "redirect:/";	// redirect�Ҷ��� redirect:url��ΰ��� �����ָ�ȴ�. 
	}
	
	//�α��ΰ�η� �̵�
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	
	
	//�α����ϰ� ������������ �̵�
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, HttpServletRequest request, HttpSession session) {	
		
		//���� ������ü���� Dao�� ���ذ����� �� (mybatis�� �̿��ؼ� ) �����͸� ����(ȭ��)���� ������� ���ϰ� ��Ʈ�ѷ����� ȣ���ؼ� �� �����~
		UserVO user = userService.selectByLogin(vo);
		
		//�α��� ����
		if(user !=null) {
			
			session =request.getSession();
			//���ο� userVO ���� session�� ��Ƽ� ����Ѵ�.
			UserVO login = new UserVO();	//���ǿ��� ��й�ȣ ��ȭ��ȣ ���������� ������ �ȵȴ�!
			login.setMidx(user.getMidx());
			login.setId(user.getId());
			login.setUserName(user.getUserName());
			
			session.setAttribute("login", login);
			
			return "redirect:/";
		}else {
			return "redirect:/user/login.do";
		}
		
		
		
	}
	
	//�α׾ƿ�. ���� �޼��� ������ ���� ������ ��Ʈ�ѷ����� ��
	@RequestMapping(value="/logout.do")
	public String logout(HttpServletRequest request, HttpSession session) {
		
		session = request.getSession();
		session.invalidate();
				
		return "redirect:/";
	}
	
	
	
	//����������
	@RequestMapping(value="/mypage.do")
	public String mypage(HttpServletRequest request, HttpSession session, Model model) {
		
		//��������
		session = request.getSession();
		//�α��� ��ü ��������
		UserVO login = (UserVO)session.getAttribute("login"); //���ǰ��� ������ƮŸ���̴ϱ� ����ȯ���ֱ�
		
		//�ش��ϴ� ȸ���� ������������ ����  
		UserVO vo = userService.selectByMypage(login.getMidx());
		
		model.addAttribute("vo", vo);
		
		
		return "user/mypage";
	}
	
	
	
	//ȸ������ ����������
	@RequestMapping(value="/modify.do", method=RequestMethod.GET)
	public String modify(int midx, Model model) {
		
		//midx�� �ش��ϴ� ȸ�������� �ѱ�� �޼���
		UserVO vo = userService.selectByMypage(midx);
		
		model.addAttribute("vo", vo);
		
		return "user/modify";
	}
	
	
	//�������� ����
	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
	public String modify(UserVO vo) {
		int result =userService.modify(vo);
		
		if(result == 1) {
			System.out.println("ȸ������ ���� ����");
		}else {
			System.out.println("ȸ������ ���� ����");
		}
		
		return "redirect:/user/mypage.do";
	}
	
	
			
	
}
