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
public class boardController {	//�극��ũ ����Ʈ �����ϰ� �����ϸ� �ű������ �����̵ǰ�, f6 ������ ���� �����͵� Ȯ�ΰ��� ����Ʈ �������� ���پ� �����ؼ� Ȯ�� �� ���ִ�. 
	
	@Autowired
	BoardService boardService;
	
	//Ds�� �����ؼ� �������⶧���� ���������ڴ� �׻� public
	@RequestMapping(value="/list.do")
	public String list(Model model, SearchVO searchVO) {	//�˻���� ����� �� �ֵ��� searchVO �ѱ�� 
		//searchVO�� ��ü���ɶ� �⺻���� null�̹Ƿ� VO��ü�� null���� Ȯ���ϴ°��� �ǹ̰� ����.
		//����Ʈ �ҷ�����
		List<BoardVO> list = boardService.list(searchVO);
		//ȭ������� �ѱ��
		model.addAttribute("list", list);
		//�Խù��� �˻��� �Ŀ� �˻�� ��� �ֵ��� ���� ��Ű�� ���� ȭ������� �ѱ��
		model.addAttribute("searchVO", searchVO);
		
		return "board/list";
	}

	//�Խñ� �ۼ��������� �̵�
	@RequestMapping(value="/write.do",  method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	//�Խñ� �ۼ� ����
	@RequestMapping(value="/write.do",  method = RequestMethod.POST)
	public void write(BoardVO vo, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException {
		// HttpServletResponse response�� ����ϸ� ��Ʈ�ѷ����� ȭ�鿡 alert ����
		
		//midx�����;��ϴϱ� session����
		session = request.getSession();
		
		//boardVO vo �� midx�� ��Ƽ� mapper���� ������ ��
		UserVO login =(UserVO)session.getAttribute("login");
		vo.setMidx(login.getMidx());
		
		//System.out.println("midx: " + vo.getMidx());
		
		//�Խñ� �ۼ�
		int result = boardService.insert(vo);
		
		//PrintWriter: ȭ�鿡�� ������ �ϴ°��̹Ƿ� response ��ü�� ����Ͽ� controller â����  ȭ��ܿ� alert�� ��� �� �ִ�. 
		//�ѱ������� utf �������ֱ�
		//���������ϴ� ���� html ����� 
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		if(result == 1) {
			System.out.println("�Խñ� ��� ����");
			//html�������� �ۼ��ϴ°��̱� ������ script �±׺��� �ۼ��Ͽ� alert�� ��� �� �ֵ��� �Ѵ�. 
			//response�� �̿��ؼ� script�� ���� redirect�� �����ʱ� ������ script�� �̿��ؼ� ȭ���� �̵������־���Ѵ�. 
			pw.append("<script>alert('�Խñ��� ���������� �ۼ��Ǿ����ϴ�.');location.href='view.do?bidx="+vo.getBidx()+"';</script>");
		}else {
			System.out.println("�Խñ� ��� ����");		
			pw.append("<script>alert('��ϵ����ʾҽ��ϴ�.');location.href='list.do'</script>");	//�� ��ü�� ȭ���� �ϳ����°ű⶧���� �̵��� script�� �ۼ�
			pw.flush();//
		}
		
		/* return "redirect:/board/list.do"; */	//return ���ϰ� if ������ alert���� �ٷ� �̵���Ŵ
	}
	
	
	//�Խñ� �� ����
	@RequestMapping(value="/view.do")
	public String view(int bidx, Model model) {
		//�Խù��ϳ� ��������(bidx�� �Ű������� �Ѱܼ� �ش��ϴ� �Խù��� ������ �´�.)
		BoardVO vo = boardService.selectOne(bidx);
		//ȭ������� ������ ��������
		model.addAttribute("vo", vo);
		//������
		return "board/view";
		
	}
	
	
	
	
}
