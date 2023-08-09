package com.wanted.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wanted.backend.dto.ArticleDTO;
import com.wanted.backend.entity.Article;
import com.wanted.backend.entity.Member;
import com.wanted.backend.repository.ArticleRepository;
import com.wanted.backend.security.JwtUtil;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private MemberService memberService;

	@Override
	public void writeArticle(ArticleDTO articleDTO) throws Exception {
		String email = JwtUtil.getCurrentMemberId(); 
		Member member = memberService.getMember(email); 
		articleRepository.save(new Article(articleDTO,member));
	}

	@Override
	public ArticleDTO modifyArticle(int num, String content) throws Exception {
		// 로그인유저정보(jwt수정)
		String email = JwtUtil.getCurrentMemberId();
		Member member = memberService.getMember(email);
		// 게시물찾기
		Optional<Article> oarticle = articleRepository.findById(num);
		if (oarticle.isEmpty())
			throw new Exception("게시물이 없습니다");
		Article article = oarticle.get();
		// 게시물글쓴이정보
		if (!member.getEmail().equals(article.getMember().getEmail()))
			throw new Exception("글쓴이만 수정 가능합니다.");
		// 게시물 수정
		article.setContent(content);
		articleRepository.save(article);
		
		return new ArticleDTO(article);
	}

	@Override
	public void removeArticle(int num) throws Exception {
		//로그인유저정보
		String email = JwtUtil.getCurrentMemberId();
		Member member = memberService.getMember(email);
		//게시물찾기
		Optional<Article> oarticle = articleRepository.findById(num);
		if(oarticle.isEmpty())throw new Exception("게시물이 없습니다");
		Article article = oarticle.get();
		//게시물글쓴이정보
		if(!member.getEmail().equals(article.getMember().getEmail()))throw new Exception("글쓴이만 삭제 가능합니다."); 
		//게시물 삭제 
		articleRepository.delete(article);
		
	}

	@Override
	public List<ArticleDTO> getArticleList(int page, Integer reqCnt) throws Exception {
		List<ArticleDTO> list = new ArrayList<>();
		if (reqCnt == null) {
			reqCnt = 10;
		}
		PageRequest pageRequest = PageRequest.of(page - 1, reqCnt, Sort.by(Sort.Direction.DESC, "num"));
		Page<Article> articles = articleRepository.findAll(pageRequest);

		for (Article a : articles) {
			list.add(new ArticleDTO(a));
		}

		return list;
	}

	@Override
	public ArticleDTO searchArticle(int num) throws Exception {
		Optional<Article> oArticle = articleRepository.findById(num);
		if(oArticle.isEmpty())throw new Exception("해당하는 게시글이 없습니다.");
		ArticleDTO articleDTO = new ArticleDTO(oArticle.get());
		return articleDTO;
	}

}
