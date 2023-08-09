package com.wanted.backend.dto;

import com.wanted.backend.entity.Article;
import com.wanted.backend.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ArticleDTO { 
	private int num;
	private String title;
	private String content;  
	private String writer;
 
	
	public ArticleDTO(Article article) {
		this.num = article.getNum();
		this.title=article.getTitle();
		this.content=article.getContent();
		this.writer=article.getMember().getEmail();
	}
	

	public ArticleDTO(String title, String content, Member member) {
		this.title = title;
		this.content = content;
		this.writer = member.getEmail();
	}
	
	
 
}
