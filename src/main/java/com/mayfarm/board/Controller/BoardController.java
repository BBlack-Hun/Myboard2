package com.mayfarm.board.Controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mayfarm.HomeController;
import com.mayfarm.board.dto.BoardDTO;
import com.mayfarm.board.service.BoardService;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private BoardService service;
	
	// 게시판 목록
	@RequestMapping(value="/board/index", method=RequestMethod.GET)
	public String index(Model model) {
		logger.info("board-index");
		model.addAttribute("list",service.list());
		return "/board/index";
	}
	
	// 게시글 작성 화면
	@RequestMapping(value="/board/writeView", method=RequestMethod.GET)
	public String WriteView(Model model) {
		logger.info("board-WriteView");
		
		return "/board/writeView";	
		
	}
	
	
}
