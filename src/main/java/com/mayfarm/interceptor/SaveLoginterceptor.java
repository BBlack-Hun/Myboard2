package com.mayfarm.interceptor;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SaveLoginterceptor extends HandlerInterceptorAdapter{
	
	// 몽고DB처리를 위해서 바로 처리가능하도록 MongoTemplate을 선언하여 넣어준다.
	// @Inject : 이용할(의존 Dependency) 객체 주입 (Inject) --> DI : JAVA
	// Autowired : 이용할(의존 Dependency) 객체 주입 (inject) --> DI : Spring
	
	@Inject
	private MongoTemplate mongoTemplate;
	
	// 처리해야할 내용의 앞에서 처리되는 인터셉터 정의 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
				
		// log에 저장할 데이터를 수집-(모듈명,요청URI, IP, log 날짜)
		String uri = request.getServletPath();
		
		// uri가 main이 아닌경우에만 처리
		if (!uri.equals("/")) {
			
			String module=uri.substring(1);
			module= module.substring(0,module.indexOf("/")); 
			String ip= request.getRemoteAddr();
			Date logDate = new Date();
			
			//객체를 만들어 데이터를 저장한다.
			LogDTO dto = new LogDTO();
			dto.setModule(module);
			dto.setUri(uri);
			dto.setIp(ip);
			dto.setLogDate(logDate);
			
			// 몽고DB에 저장한다.
			// mongoTemplate.insert(dto,컬렉션이름);
			mongoTemplate.insert(dto,"log");
		}
		
		return super.preHandle(request, response, handler);
	}

}
