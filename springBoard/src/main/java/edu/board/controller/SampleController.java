package edu.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleController {
	
	//메서드로 가상 경로들을 처리한다. 
	
	@RequestMapping(value="/sample1.do", method = RequestMethod.GET)	//링크를 누르면 get 방식 
	//가상경로를 찾으러면 @RequestMapping로 설정해준다.	//사용할 가상경로를  value에 쓰고실제 경로는 return 해준다. 
	public String sample01(Model model) {	//model은 ds가 념겨줌
		model.addAttribute("key","안녕하세요");
		
		return "/sample/sample01";	// 폴더 안에 있으므로 폴더명까지 적어준다. 
		
	}
	
	@RequestMapping(value="/sample2.do")	//method를 뺴면 재사용 불가	//원래는 가상경로를 get과 post두가지 방식으로 사용ㅎ가능
	public String sample02(int testData, Model model) {	//model은 ds가 념겨줌.	
		//매개변수 이름을 parameter 명으로 받아오면 된다. 따로 파라미터 안받아도됨. 또는  어노테이션 사용
		/*		
		String mvc에서 파라미터를 받는 방법은 매ㅐ변수 명을 파라미터 명과 일치 시킨다. 
		@Requestparam("testData") 와 같이 어노테이션을 이용할 수도 있다. 
		이때 파라미터느 ㄴ기본적으로 문자열 데이터를 가지나 기본타입에 대해서는 자동 형병변환이 가능하다.
		만약 형변환 할 수있는 데이터가 넘어오는 경우 요청 오류 발생가능
		  
		*/
		model.addAttribute("key","hello");
		System.out.println("test::::::::::::"+testData);
		
		return "/sample/sample02";	// 폴더 안에 있으므로 폴더명까지 적어준다. 
		
	}
	
	
}
