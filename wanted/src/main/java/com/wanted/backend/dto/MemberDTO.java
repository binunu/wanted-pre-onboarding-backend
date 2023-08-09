package com.wanted.backend.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.wanted.backend.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	@Pattern(regexp = ".*@.*", message = "올바른 이메일 형식을 입력해주세요")
	private String email;
	@Size(min = 8, message = "비밀번호를 8자 이상 입력해주세요")
	private String password;

	public MemberDTO(Member member) {
		this.email = member.getEmail();
		this.password = member.getPassword();
	}

}
