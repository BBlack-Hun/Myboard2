package com.mayfarm.board.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardDTO {
	
	// 몽고DB에서 _id 데이터를 가져와서 no에 처리하도록 지시한다.
	@Id
	private int no;
	private String title, content, writer;
	
	//데이터를 입력하면 날짜형 데이터로 자동으로 바꿈
	@DateTimeFormat(pattern="YYYY-MM-DD-hh:mm")
	private Date writeDate;
	private int hit;
	
	@Override
	public String toString() {
		return "BoardDTO [no=" + no + ", title=" + title + ", content=" + content +
				", writer=" + writer + ", writeDate=" + writeDate + "hit=" + hit + "]";
	}
	
}
