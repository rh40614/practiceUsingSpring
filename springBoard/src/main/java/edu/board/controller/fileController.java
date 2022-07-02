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
		//MultipartFile[] file : ������ �������̸� �迭�� ������ȴ�. 
		
		/* 
		������ �ý����� ��ο� �����ϱ� ���� request ��ü ���������. ��Ĺ�� �ٶ󺸰��ִ� ��ġ�� upload���� ã��
		����ȯ�濡�� ��ũ�����̽��ȿ� �� ������Ʈ�� was�� ����ÿ� ���� ������Ʈ ��ġ�� ��ũ�����̽��� �ƴ϶� was�� �����ϴ� ��ġ ��򰡿� ������Ʈ�� �����ؼ� ����ϰԵȴ�.
		�̶� ���ε� ���ϰ�θ� ��ũ �����̽����� ������Ʈ ���� ��θ� ����ϰ� �Ǹ�was�� �ٶ󺸴� ��ġ�� ������ ��������� �ð����� �߻��ϹǷ� ��� �ݿ��� ���� ���Ѵ�. 
		���ε� ��ġ�� ���� ��Ĺ�� ����ϴ� ������Ʈ�� �����θ� ����ϰ� �Ǹ� �ð����� �߻����� �ʾ� ��� �ݿ��� �����ϴ�.
 		(����ȯ�濡���� �߻��ϴ� ������)
 
		 */
		String path = request.getSession().getServletContext().getRealPath("/resources/upload");
		
		System.out.println(path);
		File dir = new File(path);
		if(!dir.exists()) {	//��ġ�� �����ϴ��� Ȯ��
			 dir.mkdirs();	//��ġ�� �������� �ʴ°�� ��ġ�� ����
		 }
		
		if(!file.getOriginalFilename().isEmpty()) {	//ȭ�鿡�� �Ѿ�� ������ �����Ѵٸ�
			file.transferTo(new File(path,file.getOriginalFilename()));		//error�� throw	//ȭ�鿡�� �Ѿ�� ������ path��ġ�� ���ξ��� ����
		}else {
			System.out.println("���ε��� ������ �������� �ʽ��ϴ�.");
		}
		
		return "redirect:/file/sample.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
