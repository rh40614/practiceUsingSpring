package edu.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleController {
	
	//�޼���� ���� ��ε��� ó���Ѵ�. 
	
	@RequestMapping(value="/sample1.do", method = RequestMethod.GET)	//��ũ�� ������ get ��� 
	//�����θ� ã������ @RequestMapping�� �������ش�.	//����� �����θ�  value�� ������� ��δ� return ���ش�. 
	public String sample01(Model model) {	//model�� ds�� �����
		model.addAttribute("key","�ȳ��ϼ���");
		
		return "/sample/sample01";	// ���� �ȿ� �����Ƿ� ��������� �����ش�. 
		
	}
	
	@RequestMapping(value="/sample2.do")	//method�� ���� ���� �Ұ�	//������ �����θ� get�� post�ΰ��� ������� ��뤾����
	public String sample02(int testData, Model model) {	//model�� ds�� �����.	
		//�Ű����� �̸��� parameter ������ �޾ƿ��� �ȴ�. ���� �Ķ���� �ȹ޾Ƶ���. �Ǵ�  ������̼� ���
		/*		
		String mvc���� �Ķ���͸� �޴� ����� �Ť����� ���� �Ķ���� ��� ��ġ ��Ų��. 
		@Requestparam("testData") �� ���� ������̼��� �̿��� ���� �ִ�. 
		�̶� �Ķ���ʹ� ���⺻������ ���ڿ� �����͸� ������ �⺻Ÿ�Կ� ���ؼ��� �ڵ� ������ȯ�� �����ϴ�.
		���� ����ȯ �� ���ִ� �����Ͱ� �Ѿ���� ��� ��û ���� �߻�����
		  
		*/
		model.addAttribute("key","hello");
		System.out.println("test::::::::::::"+testData);
		
		return "/sample/sample02";	// ���� �ȿ� �����Ƿ� ��������� �����ش�. 
		
	}
	
	
}
