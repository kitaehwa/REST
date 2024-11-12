package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;

// REST방식의 데이터처리
// 1) @RestController
// 2) @Controller + @ResponseBody

// @RequestBody

//@Controller
@RestController
@RequestMapping("/rest2")
public class SampleController2 {
	
	private static final Logger logger 
	 = LoggerFactory.getLogger(SampleController2.class);
	
	// http://localhost:8088/rest2/test1
	@ResponseBody
	@RequestMapping(value = "/test1",method = RequestMethod.GET)
	public String test1() {
		logger.debug(" /rest2/test1 실행 ");
		
		return "itwill";
	}
	
//	{
//		"bno":1000,
//		"title":"test1",
//		"content":"testContent1",
//		"writer" :"ITWILL부산"
//   }                                   ?bno=1000&title="test1"&....
	@RequestMapping(value = "/test2",method = RequestMethod.POST)
	public void test2(@RequestBody BoardVO vo
			//  @RequestBody => Json타입의 문자데이터를 객체의 형태로 변환
			            /* * @RequestParam("bno") int bno,
						 * 
						 * @ModelAttribute("bno") int bno2
						 */) {
		logger.debug(" /rest2/test2 실행 - POST ");
		logger.debug(" 정보를 전달받아서 처리 (출력) ");
		
//		logger.debug("bno : "+bno);
//		logger.debug("bno2 : {}",bno2);
		logger.debug("vo : {}/{}",vo,vo);
		
		
	}
	
	
	
	
}
