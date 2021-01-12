package com.mayfarm.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mayfarm.board.dao.BoardDAO;
import com.mayfarm.board.dto.BoardDTO;

@Service
public class BoardService {
	
	@Inject
	private BoardDAO dao;
	
	// 게시판 목록
	public List<BoardDTO> list() {
		return dao.list();
	}
	

}
