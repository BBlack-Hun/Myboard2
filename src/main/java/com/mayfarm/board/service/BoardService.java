package com.mayfarm.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mayfarm.board.dao.BoardDAO;
import com.mayfarm.board.dto.BoardDTO;

import net.webjjang.util.PageObject;

@Service
public class BoardService {
	
	@Inject
	private BoardDAO dao;
	
	// 게시판 목록
	public List<BoardDTO> list(PageObject pageObject) {
		pageObject.setTotalRow(dao.getTotalCount());
		return dao.list(pageObject);
	}
	
	// 글쓰기
	public void write(BoardDTO boardDTO) {
		boardDTO.setNo(dao.getNo());
		dao.write(boardDTO);
	}
	
	// 글 읽기
	public BoardDTO read(int no, int inc) {
		// 넘어오는 inc가 1인 경우에만 조회수를 1증가
		if (inc == 1)
			dao.increase(no);
		return dao.read(no);
	}
	
	// 글 수정
	public void update(BoardDTO boardDTO) {
		dao.update(boardDTO);
	}
	
	// 글 삭제
	public void delete(int no) {
		dao.delete(no);
	}

}
