package com.wanted.backend.service;

import com.wanted.backend.dto.MemberDTO;
import com.wanted.backend.entity.Member;
import com.wanted.backend.security.TokenInfo;

public interface MemberService {
	void join(MemberDTO memberDTO)throws Exception; 
	TokenInfo login(String email, String password)throws Exception;
	Member getMember(String email)throws Exception;
}
