package com.mayfarm;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	// 로그를 남기기 위한 선언
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// "/" URL에 대한 요청 처리
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		// >Console에 로그를 남겨줌 locale은 ko_KR이 반환됨 왠지는 아직 모름
		logger.info("Welcome home! The client locale is {}.", locale);
		
		// 현재 날짜, 날짜 포맷을 바꿈
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		//문자열로 변환
		String formattedDate = dateFormat.format(date);
		
		// model로 jsp로 넘겨주기 위한 값을 만듬 앞에 쓴게 jsp에서 사용할 namespace, 뒤가 무슨 값을 넘겨줄지.
		model.addAttribute("serverTime", formattedDate );
		
		ModelAndView mav = new ModelAndView("home");
		
		return mav;
	}
	
	
}
