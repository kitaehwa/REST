package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@RestController
public class TestControllerTest {
	
	@Inject
	private BoardService bService;
	
	@RequestMapping(value="/boards", method=RequestMethod.POST)
	public ResponseEntity<String> addBoard(@RequestBody BoardVO vo){
		
		
		ResponseEntity<String> respEntity = null;
		try {
			bService.regist(vo);
			respEntity = new ResponseEntity<String>("ADD_Success",HttpStatus.OK);
		} catch (Exception e) {
			respEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return respEntity;
		
	}
	
	// 글 수정
	@RequestMapping(value="/{bno}", method=RequestMethod.PUT)
	public ResponseEntity<String> updateBoard(@PathVariable("bno") int bno,
											  @RequestBody BoardVO vo){
		try {
			vo.setBno(bno);
			bService.modify(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 return new ResponseEntity<String>("updateOk", HttpStatus.OK);
	}
	
	// 글 삭제
	@RequestMapping(value="/{bno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteBoard(@PathVariable("bno") int bno){
		
		ResponseEntity<String> respEntity = null;
		try {
			bService.remove(bno);
			respEntity = new ResponseEntity<String>("deleteOK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			respEntity = new ResponseEntity<String>("deleteError", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respEntity;
	}
	
	
	
	
	
	
	
	
	
	
	
}
