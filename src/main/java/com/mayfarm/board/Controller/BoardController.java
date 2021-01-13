package com.mayfarm.board.Controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mayfarm.HomeController;
import com.mayfarm.board.dto.BoardDTO;
import com.mayfarm.board.service.BoardService;

import net.webjjang.util.PageObject;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	// 게시판 목록
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Model model, PageObject pageObject) {
		logger.info("board-index");
		model.addAttribute("list",service.list(pageObject));
		model.addAttribute("pageObject", pageObject);
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
	public String updateView(Model model, int no) {
		logger.info("board-UpdateView");
		
		// 글번호에 맞는 데이터 가져오기
		model.addAttribute("update", service.read(no, 0));
		
		return "/board/updateView";
	}
	
	// 게시글 수정
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(BoardDTO boardDTO) {
		logger.info("update");
		
		// DB에 수정 처리하러 간다. service - dao
		service.update(boardDTO);

		
		return "redirect:/board/readView?no="+boardDTO.getNo() + "&inc=0";
	}
	
	// 게시글 삭제
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(int no) {
		
		service.delete(no);
		
		return "redirect:/board/index";
	}
	
}
