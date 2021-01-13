package com.mayfarm.board.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.DbCallback;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mayfarm.board.dto.BoardDTO;
import com.mongodb.DB;
import com.mongodb.MongoException;

import net.webjjang.util.PageObject;

@Repository
public class BoardDAO {
	
	@Inject
	private MongoTemplate mongoTemplate;
	
	// 게시판 목록
	public List<BoardDTO> list(PageObject pageObject) {
		// 원하는 데이터만 가져오기 위한 Query 생성 - 조건이 없는 쿼리
		Query query = new Query();
		
		// 글의 역순으로 정렬하는 쿼리		
		query = query.with(new Sort(Direction.DESC, "no"));
		
		// 이전 페이지의 데이터는 skip 시킨다.
		query.skip((pageObject.getPage()-1) * pageObject.getPerPageNum());
		
		// 한 페이지에 표시할 데이터 만큼만 가져오기
		query.limit(pageObject.getPerPageNum());
		
		// mongoTemplate.find(조건, 돌려받을 클래스, 컬렉션)
		return mongoTemplate.find(query, BoardDTO.class, "board");
		
	}
	
	// 전체 개시글의 갯수
	public Integer getTotalCount() {
		return (int) mongoTemplate.count(new Query(),"board");
	}
	
	// 글쓰기
	public void write(BoardDTO boardDTO) {
		// 넣고싶다.. seq_no("board")
		boardDTO.setWriteDate(new Date());
		boardDTO.setHit(0);
		
		mongoTemplate.insert(boardDTO,"board");
	}
	
	// 글번호 셋팅
	public Integer getNo() {
		Query query = new Query();
		query.with(new Sort(Direction.DESC, "_id"));
		query.limit(1);
		return mongoTemplate
				.findOne(query, BoardDTO.class, "board")
				.getNo() + 1;
	}
	
	// 게시판 글 보기
	public BoardDTO read(int no) {
		System.out.println("BoardDAO.view().no:" + no);
		Query query = new Query(Criteria.where("_id").is(no));
		return mongoTemplate.findOne(query, BoardDTO.class, "board");
	}
	
	// 조회수 증가
	public void increase(int no) {
		System.out.println("BoardDAO.increase().no:"+no);
		// 조건을 적용시키는 쿼리 객체
		Query query = new Query(Criteria.where("_id").is(no));
		// 수정할 항목과 값을 적용한 수정 객체 생성
		Update update = new Update().inc("hit", 1);
		// 수정을 진행
		mongoTemplate.updateFirst(query, update, "board");
	}
	
	// 게시글 수정
	public void update(BoardDTO boardDTO) {
		
		// 조건을 설정하는 객체 - query
		Query query = new Query(Criteria.where("_id").is(boardDTO.getNo()));
		
		//  수정할 내용을 설정핮는 객체 = Update
		Update update = new Update();
		update.set("title", boardDTO.getTitle());
		update.set("content", boardDTO.getContent());
		
		mongoTemplate.updateFirst(query, update, "board");
		
	}
	
	// 게시글 삭제
	public void delete(int no) {
		Query query = new Query(Criteria.where("_id").is(no));
		
		
		mongoTemplate.remove(query, "board");
	}
}