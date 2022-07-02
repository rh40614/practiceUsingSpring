package edu.board.service;

import edu.board.vo.UserVO;

//interface�� ������ �ν��Ͻ��� ������ �� �� �����ϱ� ioc �����̳ʰ� ���� �ʾƵ� �ȴ�. 
public interface UserService {
	
	//ȸ������������ ȭ�鿡���ϴ°��� �����͸� �ִ°��̹Ƿ� �װͿ� ���߾� �̸�����
	int insertUser(UserVO vo);
	//�α��� �޼���
	UserVO selectByLogin(UserVO vo);
	//���������� 
	UserVO selectByMypage(int midx);
	//ȸ������ ����
	int modify (UserVO vo);
	
}
