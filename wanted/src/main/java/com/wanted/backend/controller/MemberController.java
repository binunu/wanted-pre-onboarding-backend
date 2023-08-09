package com.wanted.backend.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.backend.dto.MemberDTO;
import com.wanted.backend.security.TokenInfo;
import com.wanted.backend.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MemberService memberService;
	
	//과제 1. 사용자 회원가입 엔드포인트
	@PostMapping("/join")
	public ResponseEntity<String> join(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult) {
		try {
			String encodedPw = passwordEncoder.encode(memberDTO.getPassword());
			memberDTO.setPassword(encodedPw);
			memberService.join(memberDTO);
			 if (bindingResult.hasErrors()) {
				 String errorMessage = bindingResult.getFieldErrors().stream()
					        .map(error -> error.getDefaultMessage())
					        .reduce("", (accumulator, errorMessageItem) -> accumulator + " " + errorMessageItem);
				throw new Exception(errorMessage);
			 }
			return new ResponseEntity<String>("회원가입 성공!",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	//과제 2. 사용자 로그인 엔드포인트
	@PostMapping("/login")
	public ResponseEntity<Map<String,Object>> login(@ModelAttribute MemberDTO memberDTO) {
		Map<String,Object> res = new HashMap<>();
		try { 
			String id = memberDTO.getEmail();
			String pw = memberDTO.getPassword();
			if(!id.contains("@"))throw new Exception("올바른 이메일 형식을 입력해 주세요");
			if(pw.length()<8)throw new Exception("비밀번호를 8자 이상 입력해주세요");
			
			TokenInfo tokenInfo = memberService.login(id, pw); 
			res.put("tokenInfo", tokenInfo);
			return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			res.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(res,HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
