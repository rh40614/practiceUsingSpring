package edu.board.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller	//@Controller는 @Component 어노테이션의 하위에 존재하는 어노테이션이기 떄문에 ioc 컨트롤러를 찾는다.
//스프링에서 컨트롤러는 싱글톤구조이다. controller 하나 
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET) //요청받은 경로(value)와   특정 메서드를 맵핑		//프로젝트이름 뒤의  /는 홈. 
	public String home(Locale locale, Model model) {	//매개변수 model이 Vo 데이터를 담는역할
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		 
		model.addAttribute("serverTime", formattedDate );	//model 에 담았다는것은 화면에 데이터를 넘김
		//model.addAllAttributes("hello",)
		
		return "home";	//이 반환값에 prefix와 surffix가 붙게 됨
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
