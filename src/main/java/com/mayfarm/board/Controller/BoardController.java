package com.mayfarm.board.Controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mayfarm.HomeController;
import com.mayfarm.board.dto.BoardDTO;
import com.mayfarm.board.service.BoardService;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	// 게시판 목록
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Model model) {
		logger.info("board-index");
		model.addAttribute("list",service.list());
		return "/board/index";
	}
	
	// 게시글 작성 화면
	@RequestMapping(value="/writeView", method=RequestMethod.GET)
	public String WriteView() {
		logger.info("board-WriteView");	
		
		return "/board/writeView";	
		
	}
	
	// 게시글 작성
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(BoardDTO boardDTO) {
		logger.info("write");
		service.write(boardDTO);
		
		return "redirect:/board/index";
	}
	
	// 게시글 보기
	@RequestMapping(value="/readView", method=RequestMethod.GET)
	public String read(Model model, int no, int inc) {
		logger.info("read");
		
		// DB에서 데이터를 가져온다.
		model.addAttribute("dto", service.read(no, inc));
		
		return "/board/readView";
	}
	
	// 게시글 수정 뷰
	@RequestMapping(value="/updateView", method=RequestMethod.GET)
	public String updateView(Model model, BoardDTO boardDTO) {
		logger.info("board-UpdateView");
		
		model.addAttribute("update", service.read(boardDTO.getNo(), 1));
		
		
		System.out.println(boardDTO.getNo());
		
		return "/board/updateView";
	}
	
	// 게시글 수정
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(BoardDTO boardDTO, RedirectAttributes rttr) {
		logger.info("update");
		
		service.update(boardDTO);
		
		System.out.println("어디서 나왔는가????" + boardDTO.getNo());
		
		// 취소 눌렀을때, 리다이렉션하기 ㅎㅎ
		rttr.addAttribute("inc", 1);
		
		return "redirect:/board/readView";
	}
	
	// 게시글 삭제
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(BoardDTO boardDTO) {
		
		service.delete(boardDTO);
		
		return "redirect:/board/index";
	}
	
}
