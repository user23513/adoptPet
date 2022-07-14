package co.yedam.puppy.heart.service;

import java.util.List;

import co.yedam.puppy.heart.vo.HeartVO;

public interface HeartService {
	//경아
	List<HeartVO> myHeartList(int startRow, int pageSize); //내가누른하트리스트
}
