package com.wanted.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.backend.dto.ArticleDTO;
import com.wanted.backend.service.ArticleService;

@RestController
@RequestMapping("/board")
public class ArticleController {

	@Autowired
	private ArticleService articleService; 
	
	//과제3. 새로운 게시글을 생성하는 엔드포인트
	@PostMapping("/writeArticle")
	public String writeArticle(@ModelAttribute ArticleDTO articleDTO) {
		try { 
			articleService.writeArticle(articleDTO); 
			return "게시글 작성 완료!";
		}catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	//과제4. 게시글 목록 조회
	@GetMapping("/articleList/{page}")
	public ResponseEntity<Map<String,Object>> articleList(@PathVariable Integer page, @RequestParam(value="reqCnt",required=false) Integer reqCnt) {
		Map<String,Object> res = new HashMap<>();
		try {
			List<ArticleDTO> list = articleService.getArticleList(page, reqCnt);
			res.put("list",list);
			return new ResponseEntity<Map<String,Object>>(res,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			res.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(res,HttpStatus.BAD_REQUEST);
		}
	}
	
	//과제 5. 특정 게시글을 조회하는 엔드포인트
	@GetMapping("/searchArticle/{num}")
	public ResponseEntity<ArticleDTO> searchArticle(@PathVariable Integer num) {
		try {
			ArticleDTO articleDTO = articleService.searchArticle(num);
			return new ResponseEntity<ArticleDTO>(articleDTO,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace(); 
			return new ResponseEntity<ArticleDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	//과제 6. 특정 게시글을 수정하는 엔드포인트
		@PutMapping("/modifyArticle/{num}")
		public ResponseEntity<Map<String,Object>> modifyArticle(@PathVariable Integer num,@RequestBody String content ) {
			Map<String,Object> res = new HashMap<>();
			try {
				ArticleDTO articleDTO = articleService.modifyArticle(num,content);
				res.put("article", articleDTO);
				return new ResponseEntity<Map<String,Object>>(res,HttpStatus.OK);
			}catch(Exception e) {
				e.printStackTrace(); 
				return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
			}
		}

	//과제 7. 특정 게시글을 삭제하는 엔드포인트
	@DeleteMapping("/removeArticle/{num}")
	public ResponseEntity<String> login(@PathVariable Integer num) {
		try {
			articleService.removeArticle(num);
			return new ResponseEntity<String>("게시글 삭제 성공",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
