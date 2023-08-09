# wanted-pre-onboarding-backend

- 성명
  : 홍성빈
- 애플리케이션의 실행 방법 (엔드포인트 호출 방법 포함)
- 데이터베이스 테이블 구조
- 구현한 API의 동작을 촬영한 데모 영상 링크

  
- 구현 방법 및 이유에 대한 간략한 설명

- API 명세

|method|url|설명|request|response|
|------|---|---|---|---|
|post|/member/join|회원가입|MemberDTO|String|
|post|/member/login|로그인|MemberDTO|TokenInfo|
|post|/board/writeArticle|게시글작성|ArticleDTO|ArticleDTO|
|get|/board/articleList/{page}|게시글목록|reqCnt(페이지당 글 수)|List<ArticleDTO>|
|get|/board/searchArticle/{num}|게시글조회|int num|ArticleDTO|
|put|/board/modifyArticle/{num}|게시글수정|int num|ArticldDTO|
|delete|/board/removeArticle/{num}|테스트3|int num|String|
