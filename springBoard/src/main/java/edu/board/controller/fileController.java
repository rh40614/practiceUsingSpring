package edu.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping(value="/file")
@Controller
public class fileController {

	
	@RequestMapping(value="/sample.do")
	public String sample() {
		return "file/sample";
	}
	
	
	@RequestMapping(value="/upload.do", method=RequestMethod.POST)
	public String upload(MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {	
		//MultipartFile[] file : 사진이 여러건이면 배열로 받으면된다. 
		
		/* 
		파일을 시스템의 경로에 저장하기 위해 request 객체 가지고오기. 톰캣이 바라보고있는 위치에 upload폴더 찾기
		개발환경에서 워크스페이스안에 둔 프로젝트를 was로 실행시에 실제 프로젝트 위치는 워크스페이스가 아니라 was가 실행하는 위치 어딘가에 프로젝트르 복사해서 사용하게된다.
		이때 업로드 파일결로를 워크 스페이스내의 프로젝트 절대 경로를 사용하게 되면was가 바라보는 위치로 파일을 복사라기까지 시간차가 발생하므로 즉시 반영이 되지 못한다. 
		업로드 위치를 실제 톰캣이 사용하는 프로젝트의 리얼경로를 사용하게 되면 시간차가 발생하지 않아 즉시 반영이 가능하다.
 		(개발환경에서만 발생하는 문제점)
 
		 */
		String path = request.getSession().getServletContext().getRealPath("/resources/upload");
		
		System.out.println(path);
		File dir = new File(path);
		if(!dir.exists()) {	//위치가 존재하는지 확인
			 dir.mkdirs();	//위치가 존재하지 않는경우 위치를 생성
		 }
		
		if(!file.getOriginalFilename().isEmpty()) {	//화면에서 넘어온 파일이 존재한다면
			file.transferTo(new File(path,file.getOriginalFilename()));		//error는 throw	//화면에서 넘어온 파일을 path위치에 새로쓰는 로직
		}else {
			System.out.println("업로드할 파일이 존재하지 않습니다.");
		}
		
		return "redirect:/file/sample.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
