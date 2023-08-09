package com.wanted.backend.service;

import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.wanted.backend.dto.MemberDTO;
import com.wanted.backend.entity.Member;
import com.wanted.backend.repository.MemberRepository;
import com.wanted.backend.security.JwtProvider;
import com.wanted.backend.security.TokenInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtProvider jwtProvider; 
	
	@Override
	public void join(MemberDTO memberDTO) throws Exception {
		memberRepository.save(new Member(memberDTO));
	}
	@Override
	public TokenInfo login(String email, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		TokenInfo tokenInfo = jwtProvider.createToken(authentication);
		return tokenInfo;
	}
	
	@Override
	public Member getMember(String email) throws Exception {
		Optional<Member> omember = memberRepository.findByEmail(email);
		if(omember.isEmpty())throw new Exception("유저정보가 없습니다");
		return omember.get();
	}
	

}
