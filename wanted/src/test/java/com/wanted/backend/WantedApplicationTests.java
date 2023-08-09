package com.wanted.backend;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.wanted.backend.controller.ArticleController;
import com.wanted.backend.controller.MemberController;
import com.wanted.backend.dto.ArticleDTO;
import com.wanted.backend.dto.MemberDTO;
import com.wanted.backend.repository.MemberRepository;
import com.wanted.backend.service.ArticleService;
import com.wanted.backend.service.MemberService;

@SpringBootTest
class WantedApplicationTests {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleController articleController;
	@Autowired
	private MemberController memberController;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberService userService;
	@Autowired
	private MemberRepository userRepository;
	

	@Test
	void contextLoads() {
	}
	//과제 1. 사용자 회원가입 엔드포인트
	@Test
	void join() {
		try {
			MemberDTO memberDTO = new MemberDTO("test1@wanted.com","12345678"); 
			BindingResult bindingResult = new BeanPropertyBindingResult(memberDTO, "memberDTO");
			memberController.join(memberDTO,bindingResult);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//과제 2. 사용자 로그인 엔드포인트
	@Test
	void login() {
		try {
			MemberDTO memberDTO = new MemberDTO("test1@wanted.com","12345678"); 
			ResponseEntity<Map<String,Object>> res = memberController.login(memberDTO);
			System.out.println(res.getBody().get("tokenInfo"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//과제 3. 새로운 게시글을 생성하는 엔드포인트
	@Test
	void writeArticle(){
		String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUB3YW50ZWQuY29tIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY5MTU2MTc4Nn0.ilVvcCJ7bjpR6u8sEgl-A99-pyz0UE_Y4vJUSodaGaI";
		String jwtToken2 = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTE1NjE3ODZ9.ege4oc5NhARIBF-uIJRcP9BnczPDJ0lElVrvtMba300";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);
		
		try {
			for(int i = 1; i<= 10; i++) {
				ArticleDTO articleDTO = ArticleDTO.builder().title("제목"+i).content("내용"+i).build();
				articleController.writeArticle(articleDTO);
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}  
	}
	
	//과제 4. 게시글 목록을 조회하는 엔드포인트
		@Test
		void articleList(){
			try {
				List<ArticleDTO> list = articleService.getArticleList(2,3);
				System.out.println(list);
			}catch(Exception e) {
				e.printStackTrace();
			}  
		}
		
	//과제 5. 특정 게시글을 조회하는 엔드포인트
	@Test
	void searchArticle(){
		try {
			ArticleDTO articleDTO = articleService.searchArticle(1);
			System.out.println(articleDTO);
		}catch(Exception e) {
			e.printStackTrace();
		}  
	}
	//과제 6. 특정 게시글을 수정하는 엔드포인트
	@Test
	void modifyArticle(){
		try {
			articleService.modifyArticle(1, "수정된 내용1");
		}catch(Exception e) {
			e.printStackTrace();
		}  
	}
	//과제 7. 특정 게시글을 삭제하는 엔드포인트
	@Test
	void removeArticle(){
		try {
			articleService.removeArticle(2);
		}catch(Exception e) {
			e.printStackTrace();
		}  
	}

}
