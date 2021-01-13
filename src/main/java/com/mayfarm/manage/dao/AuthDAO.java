package com.mayfarm.manage.dao;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mayfarm.manage.dto.AuthDTO;

@Repository
public class AuthDAO {
	
	@Inject
	private MongoTemplate mongoTemplate;
	// 회원가입
	public void register(AuthDTO authDTO) {
		authDTO.setWriteDate(new Date());
		authDTO.setAuth("ROLE_ADMIN");
		mongoTemplate.insert(authDTO, "users"); 
	}
}
