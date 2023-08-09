package com.wanted.backend.service;

import java.util.List;

import com.wanted.backend.dto.ArticleDTO;

public interface ArticleService {
	public void writeArticle(ArticleDTO articleDTO)throws Exception;
	List<ArticleDTO> getArticleList(int num, Integer reqCnt)throws Exception;
	ArticleDTO searchArticle(int num)throws Exception;
	ArticleDTO modifyArticle(int num, String content)throws Exception;
	void removeArticle(int num)throws Exception;
}
