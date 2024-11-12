package com.itwillbs.controller;

import java.util.List;

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

/**
 *	 REST방식 요청되는 기본형태
 *   =>  /작업명/기본키 + 메서드 + 데이터
 *   
 *    작업명 : 요청하려는 작업종류(글쓰기,글조회,글 수정,글삭제...)
 *    기본키 : 요청해놓은 작업에 필요한 기본키값(전달되는값)
 *    데이터 : 요청작업에 필요한 데이터(JSON)
 *    메서드 : 요청하는 기능
 *            (요청방식 : GET(조회-Select/Read)
 *            			  POST(생성-Create)
 *             			  PUT(수정-Update) / PATCH(일부수정)
 *					      DELETE(삭제-Delete)
 *
 *
 *     글쓰기 : /boards + 데이터(JSON)  POST방식
 *     글조회 : /boards/all  GET방식(ALL) 
 *     글조회 : /boards/100  GET방식(특정글) 
 *     글수정 : /boards/100 + 데이터(JSON)	PUT방식
 *     글삭제 : /boards/100 	DELETE방식 
 *
 */
@RestController
@RequestMapping(value = "/boards")
public class BoardRESTController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardRESTController.class);
	
	
	@Inject
	private BoardService bService;
	
	
	// 글 쓰기 : /boards + 데이터(JSON)  POST방식
	@RequestMapping(value = "",method = RequestMethod.POST)
	public ResponseEntity<String> addBoard(@RequestBody BoardVO vo){
		logger.info("addBoard() 실행");
		logger.info("vo : {}",vo);
		
		ResponseEntity<String> respEntity = null;
		try {
			logger.info(" 서비스.regist(vo) 호출 ");
			//서비스.regist(vo);호출 => DAO => DB저장
			bService.regist(vo);
			respEntity = new ResponseEntity<String>("ADD_Success",HttpStatus.OK);
		}catch (Exception e) {
			//respEntity = new ResponseEntity<String>("ADD_Error",HttpStatus.BAD_REQUEST);
			respEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return respEntity;
	}
	
	//글조회 : /boards/all  GET방식(ALL) 
	// 글목록 전부(all) 조회
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	public ResponseEntity<List<BoardVO>> listAllBoard(){
		logger.info(" listAllBoard()  호출 ");

		ResponseEntity<List<BoardVO>> respEntity = null;
		try {
			List<BoardVO> boardList = bService.listAll();
			
			respEntity 
			 = new ResponseEntity<List<BoardVO>>(boardList,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respEntity;
	}
	
	//  글조회 : /boards/1  GET방식(특정글) 
	@RequestMapping(value = "/{bno}",method = RequestMethod.GET)
	public ResponseEntity<BoardVO> readBoard(@PathVariable("bno") int bno){
		logger.info("readBoard() 실행");
		
		ResponseEntity<BoardVO> result = null;
		try {
			BoardVO vo = bService.read(bno);
			result = new ResponseEntity<BoardVO>(vo,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	//  글수정 : /boards/100 + 데이터(JSON)	PUT방식
	@RequestMapping(value = "/{bno}",method = RequestMethod.PUT )
	public ResponseEntity<String> updateBoard(@PathVariable("bno") int bno 
			                                 , @RequestBody BoardVO vo){
		
		try {
			vo.setBno(bno);
			bService.modify(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("updateOK",HttpStatus.OK);		
	}
	
	// 글삭제 : /boards/100 
	@RequestMapping(value="/{bno}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteBoard(@PathVariable("bno") int bno){
		
		ResponseEntity<String> respEntity=null;
		
		try {
			bService.remove(bno);
			respEntity = new ResponseEntity<String>("deleteOK",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			respEntity = new ResponseEntity<String>("deleteErr",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respEntity;
	}
	
	
	
	

}
