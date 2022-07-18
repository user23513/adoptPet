package co.yedam.puppy.petList.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.board.service.BoardService;
import co.yedam.puppy.board.service.BoardServiceImpl;
import co.yedam.puppy.boardPart.service.BoardPartService;
import co.yedam.puppy.boardPart.service.BoardPartServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.BoardPartVO;

public class PetListForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//입양동물 소개게시판 게시글 등록페이지로 이동
		BoardPartService dao = new BoardPartServiceImpl();
		List<BoardPartVO> list = dao.boardPartList();
		
		request.setAttribute("boardPartList", list);
		
		return "petList/petListForm";
	}

}
