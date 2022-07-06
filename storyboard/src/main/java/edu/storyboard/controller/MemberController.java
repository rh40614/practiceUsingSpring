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
	
	
	
	//ȸ������
	@RequestMapping(value="/join.do" , method=RequestMethod.POST)
	public void insertMember(MemberVO vo, HttpServletResponse response) throws IOException {
		
		//ȭ��� alert�ϰ� �ٷ� �̵�
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		int result = memberService.insertMember(vo);
		if(result == 1) {
			System.out.println("ȸ������ ����");
			pw.append("<script>alert('ȸ�������� �����մϴ�.');location.href='../'</script>;");
		}else {
			System.out.println("ȸ������ ����");
			pw.append("<script>alert('ȸ�����Կ� �����Ͽ����ϴ�. �ٽ� �������ֽʽÿ�');location.href='join.do'</script>;");
			pw.flush();	//������ ������ �����
		}	
	}
	
	@RequestMapping(value="/login.do" , method=RequestMethod.GET)
	public String login() {
		return"member/login";
	}
	
	//�α���
	@RequestMapping(value="/login.do" , method=RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest request, HttpSession session) {
		
		//�α����� ȸ���� �������
		MemberVO member = memberService.login(vo);
		
		if(member != null) {
			System.out.println("�α��� ����");
			session = request.getSession();
			
			//���ο� vo ���� sessiond�� ������ ������ �ʿ��� �͸� ��Ƽ� ����ϱ�
			MemberVO login = new MemberVO();
			login.setMidx(member.getMidx());
			login.setMemberName(member.getMemberName());
			login.setMemberId(member.getMemberId());
			
			session.setAttribute("login", login);
//�α��� ���� �������� ����������� �̵�			
			return "redirect:/";									
		}else {
			return"redirect:/member/login.do";
		}
		
	}
	
	
	//�α׾ƿ�
	@RequestMapping(value="/logout.do")
	public void logout(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
		
		session = request.getSession();
		session.invalidate();
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		pw.append("<script>alert('�̿����ּż� �����մϴ�.');location.href='../'</script>;");
		/* return "redirect:/"; */
	}
	
	
	//���̵� �ߺ��˻�
	@ResponseBody
	@RequestMapping(value="/idCheck.do", produces = "application/json;charset=utf-8")
	public Map<Object, Object> idcheck(MemberVO vo) {
	        
	Map<Object, Object> map = new HashMap<Object, Object>();
	 
	//db�� �ִ� �����Ϳ� ���Ͽ� ���̵� �ִٸ� 1�� ��ȯ�ϰ� ������ 0�� ��ȯ�ϱ�
	int count = memberService.idCheck(vo);
	map.put("cnt", count);
	
	System.out.println("count: "+ count);

	return map;
	}
	
	
	
	
	
	
	
	
}
