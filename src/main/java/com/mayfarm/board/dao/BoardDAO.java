package com.mayfarm.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mayfarm.board.dto.BoardDTO;

@Repository
public class BoardDAO {
	
	@Inject
	private MongoTemplate mongoTemplate;
	
	// 게시판 목록
	public List<BoardDTO> list() {
		System.out.println(getClass().getSimpleName()+".list()");
		Query query = new Query().with(new Sort(Direction.DESC, "no"));
		
		// mongoTemplate.find(조건, 돌려받을 클래스, 컬렉션)
		return mongoTemplate.find(query, BoardDTO.class, "board");
	}
	
}
