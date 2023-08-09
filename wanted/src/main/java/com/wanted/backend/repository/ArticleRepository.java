package com.wanted.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.backend.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
//	Page<Article> findAll(PageRequest pageRequest)throws Exception;
}	
