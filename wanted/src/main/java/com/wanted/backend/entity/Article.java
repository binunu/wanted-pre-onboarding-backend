package com.wanted.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.wanted.backend.dto.ArticleDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Article {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer num;
	@Column
	private String title;
	@Column
	private String content; 
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="writer")  
	private Member member; 
	 
	@Builder
	public Article(ArticleDTO articleDTO, Member member) {
		this.num = articleDTO.getNum();
		this.title = articleDTO.getTitle();
		this.content=articleDTO.getContent();
		this.member=member;
	}
	
	
}
