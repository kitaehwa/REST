package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;

//@RestController : 모든 매핑된 메서드가 @ResponseBody을 포함하고 있다

@RestController
@RequestMapping("/rest")
public class SampleController1 {

	private static final Logger logger 
	   = LoggerFactory.getLogger(SampleController1.class);
	
	// http://localhost:8088/rest/test1
	// 메서드 사용 동작 구현
//	@ResponseBody
	@RequestMapping(value = "/test1",method = RequestMethod.GET)
	public /* @ResponseBody */void test1() {
		logger.debug(" test1() 실행 ");
	}
	
	// http://localhost:8088/rest/test2
	@RequestMapping(value = "/test2",method = RequestMethod.GET)
	public String test2() {
		logger.debug(" test2() 실행 ");
		return "<h1>test2</h1>";
	}
	
	// http://localhost:8088/rest/test3
	@RequestMapping(value = "/test3",method = RequestMethod.GET)
	public int/*double*/ test3() {
		logger.debug(" test3() 실행 ");
		// jackson-databind라이브러리가
		// 리턴하는 데이터를 Json 형태로 변환
		return 1000;
	}
	
	// JSON표현가능
	// 문자열,숫자(정수,실수),boolean(true,false),null,객체{ },배열 [ ]
	
	// http://localhost:8088/rest/test4
	@RequestMapping(value = "/test4",method = RequestMethod.GET)
	public BoardVO test4() {
		logger.debug(" test4() 실행 ");
		// jackson-databind라이브러리가
		
		BoardVO vo = new BoardVO();
		vo.setBno(100);
		vo.setTitle("테스트");
		vo.setContent("내용1234");
		vo.setWriter("작성자1");
		
		return vo;
	}
	
	// http://localhost:8088/rest/test5
	@RequestMapping(value = "/test5",method = RequestMethod.GET)
	public ArrayList<BoardVO> test5() {
		logger.debug(" test5() 실행 ");
		// jackson-databind라이브러리가
		
		ArrayList<BoardVO> boardList = new ArrayList<BoardVO>();
		for(int i=0;i<5;i++) {
			BoardVO vo = new BoardVO();
			vo.setBno(100);
			vo.setTitle("테스트"+i);
			vo.setContent("내용1234"+i);
			vo.setWriter("작성자"+i);
			
			boardList.add(vo);
		}
		return boardList;
	}
	
	// http://localhost:8088/rest/test6
	@RequestMapping(value = "/test6",method = RequestMethod.GET)
	public Map<Integer, BoardVO> test6() {
		logger.debug(" test6() 실행 ");
		// jackson-databind라이브러리

		Map<Integer, BoardVO> boardMap = new HashMap<Integer, BoardVO>();
		for(int i=0;i<5;i++) {
			BoardVO vo = new BoardVO();
			vo.setBno(100);
			vo.setTitle("테스트"+i);
			vo.setContent("내용1234"+i);
			vo.setWriter("작성자"+i);
			
			boardMap.put(i, vo);
		}
		return boardMap;
	}
	
	
	// http://localhost:8088/rest/test7?number=100
	// http://localhost:8088/rest/test7/100
	// http://localhost:8088/rest/test7/itwill
	// http://localhost:8088/rest/test7
	//@RequestMapping(value = "/test7",method = RequestMethod.GET)
	@RequestMapping(value = "/test7/{num}",method = RequestMethod.GET)
	public String test7(@PathVariable("num") Integer num /* @ModelAttribute("number") int number */) {
		logger.debug("test7()");
//		logger.debug(" number : "+num);
		
		return "";
	}
	
	
	/**
	 * Http 상태코드
	 * https://developer.mozilla.org/ko/docs/Web/HTTP/Status
	 * 
	 * 100번대 : 처리중인 데이터의 상태
	 * 100 : 데이터의 일부를 서버가 받은상태
	 * 
	 * 200번대 : 정상적인 응답
	 * 200 : 에러없이 정상 처리
	 * 201 : 요청이 성공적으로 처리, 그결과 새로운 리소스 생성
	 * 
	 * 300번대 : 다른 URL을 처리 (리다이렉트시 문제)
	 * 301 : 요청한 페이지가 새 URL으로 변경
	 * 304 : 기존의 데이터와 변경이 없는경우
	 * 
	 * 400번대 : 서버에서 인식할 수 없음
	 *  400 : 요청에 문제가 있는경우 (데이터를 잘못 전달하는 경우)
	 *  403 : 서버에서 허락X(권한)
	 *  404 : URL에 해당하는 데이터가 없음
	 *  405 : HTTP메서드 지원X
	 *  
	 * 
	 * 500번대 : 서버 내부에러
	 * 500 : 서버가 데이터 처리시 에러발생
	 * 502 : 게이트웨이나 프록시상태 문제(과부하)
	 * 503 : 일시적 과부화/서비스 중단상태
	 * 504 : 처리시간을 지난 요청(처리 불가능) 
	 * 
	 * 
	 * 
	 */
	// http://localhost:8088/rest/test8
	// 주소 호출을 통해서 데이터를 생성 + 상태
	@RequestMapping(value = "/test8",method = RequestMethod.GET)
	public ResponseEntity<Void> test8() {
		logger.debug("test8()");
	
//		if(true) {
//			//return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//			
//		}else {
//			//return new ResponseEntity<Void>(HttpStatus.OK);			
//		}
//		
//		try {
//			//return new ResponseEntity<Void>(HttpStatus.OK);			
//		}catch() {
//			//return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//		}
		
		//ResponseEntity<T>
		// => 결과의 데이터+HTTP상태코드 포함하는 객체
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// http://localhost:8088/rest/test9
	@RequestMapping(value = "/test9",method = RequestMethod.GET)
	public ResponseEntity<String> test9() {
//	public ResponseEntity<List<BoardVO>> test9() {
		logger.debug("test9()");
		//return new ResponseEntity<String>("Result",HttpStatus.OK);
		//return new ResponseEntity<String>("Result",HttpStatus.NOT_FOUND);
		//return new ResponseEntity<String>("ErrData",HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity("ErrData",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// http://localhost:8088/rest/test10
	@RequestMapping(value = "/test10",method = RequestMethod.GET)
	public ResponseEntity<String> test10(){
		logger.debug(" test10() 실행 ");
		
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.add("Content-Type", "text/html; charset=UTF-8");
		
		String tagData = "<script>";
		tagData += " alert('REST컨트롤러로 실행!'); ";
		tagData += "</script>";
		
		//return new ResponseEntity<String>("data",HttpStatus.OK);
		return new ResponseEntity<String>(tagData,respHeaders,HttpStatus.OK);
	}
	
	
	
	
	

}// SampleController1
