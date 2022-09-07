# azt

강아지를 위한 웹사이트

기본적인 게시판 crud 연습을 위한 코드 작성

# 1. 개요
 - 개인프로젝트
 - 주요 기능
 
   - 사용자 : Spring Security를 이용한 회원가입 및 로그인, 유효성 검사
   - 게시판 : CRUD 기능, 페이징 및 검색
   - 댓글 : 작성, 삭제
   
- 개발 언어 : Java 17
- 개발 환경 : Spring Boot 2.7.3, Gradle 7.5, JPA (Spring Date JPA), Spring Security, Thymeleaf
- 데이터베이스 : MySQL 8.0.29
- 형상관리 툴 : GitHub, GitKraken

# 2. 요구사항 분석
### 회원가입
     1. 유효성 검사
     
        - 아이디와 비밀번호는 필수값 
        - 이메일 형식 검사
        
     2. 중복 확인
     
        - 데이터 베이스에 존재하는 아이디를 입력한 후 회원가입 버튼을 누르면 안내 메세지 출력
         
 ### 로그인
      1. 로그인 하지 않은 경우 접속 가능 페이지
      
        - 회원가입 페이지
        - 로그인 페이지
        - 게시글 목록 페이지
        - 게시글 상세보기 페이지
        
      2. 로그인 검사
      
        - 아이디가 없거나, 비밀번호가 일치하지 않는 경우 안내 메시지 출력
        - 회원가입 후 index 페이지로 이동
        
### 게시글

    1. 로그인한 회원만 게시글 작성 가능
    2. 내가 작성한 글만 수정, 삭제 가능
    3. 게시글 작성시 제목, 게시글은 공백으로 작성하지 않게하기 ( JavaScript 이용 )
    
### 댓글

    1. 로그인한 회원만 댓글 작성 가능
    2. 내가 작성한 댓글만 수정, 삭제 가능
    3. 게시글 삭제 시 해당 댓글 데이터도 같이 삭제되게 하기
# DB 설계
![KakaoTalk_20220906_231446947](https://user-images.githubusercontent.com/102720472/188658695-271bd88f-d496-4902-8c2d-c327646a57d5.png)
# 주요 기능
<details>
  <summary>회원가입</summary>
  
  - 유효성 검사
  
 ![KakaoTalk_20220906_233131043](https://user-images.githubusercontent.com/102720472/188663786-7e9e4b0c-2172-4878-a552-318912bb001d.png)

 - 회원가입 완료시 로그인 페이지 이동
 
 ![KakaoTalk_20220906_234546245](https://user-images.githubusercontent.com/102720472/188665513-c133164b-1829-447e-8ea9-658e65cd239c.png)

  
</details>

<details>
  <summary>로그인</summary>
  
  - 유효성 검사
  
![KakaoTalk_20220906_234001354](https://user-images.githubusercontent.com/102720472/188664195-f2a8f327-b605-4568-a2ef-00f717eecfe1.png)

 - 로그인 완료시 index 페이지 이동
 
 ![KakaoTalk_20220906_234350542](https://user-images.githubusercontent.com/102720472/188665145-b16d5e18-cfcd-4144-ae67-d1153da4ae13.png)

  
</details>
<details>
  <summary>게시판</summary>
  
  
  - 게시글 목록( 페이징 )
  
  ![KakaoTalk_20220906_234350542](https://user-images.githubusercontent.com/102720472/188667910-6b8c8e9a-0844-469d-b1a3-3dcd3130f35c.png)
  
  - 게시글 검색 ( 제목, 본문, 해시태그, 작성자 )
  
    - 대소문자 구분 X, 부분 문자열 검색
  
  ![KakaoTalk_20220906_235529502](https://user-images.githubusercontent.com/102720472/188668005-4417d6d4-3a6a-4f9e-a328-6fb2d5b69691.png)
  
  - 게시글 작성
  
    - 게시글 작성 폼
    
![KakaoTalk_20220907_000323543](https://user-images.githubusercontent.com/102720472/188669687-f3b08061-4e65-47f4-bcfd-a795580b7349.png)
  
  - 유효성 검사 ( JavaScript )
    
    
![KakaoTalk_20220907_000122737](https://user-images.githubusercontent.com/102720472/188669769-f9b148ea-dc29-4463-8cc3-b05803acb371.png)

 ![KakaoTalk_20220907_000105656](https://user-images.githubusercontent.com/102720472/188669778-d1ac4c70-3dc6-4923-bbc8-bf98f43fbf1b.png)
 
 - 게시글 작성 후 게시글 목록으로 이동
 
 ![KakaoTalk_20220907_001151089](https://user-images.githubusercontent.com/102720472/188671495-b2738539-e162-46ff-acd0-d52524287004.png)


  - 게시글 수정/삭제
  
    - 본인 게시글만 수정 삭제 가능
    
    ![KakaoTalk_20220907_001421705](https://user-images.githubusercontent.com/102720472/188672281-55731824-c884-4d42-85f1-c02c54a42465.png)

    
    - 수정 폼
    
    ![KakaoTalk_20220907_001440949](https://user-images.githubusercontent.com/102720472/188672315-b478bab4-4aef-415e-81dc-a02468a23ca4.png)

    
    - 수정 및 삭제 후 게시글 목록으로 이동
    
    ![KakaoTalk_20220907_001507572](https://user-images.githubusercontent.com/102720472/188672352-f8acfbeb-ac7b-404f-bb79-675ebf4df429.png)

    
  
</details>

<details>
  <summary>댓글</summary>
  
  - 로그인 하지 않고 댓글 쓸 경우 로그인으로 이동
  
  - 댓글 쓰기
  
  ![KakaoTalk_20220907_001916434](https://user-images.githubusercontent.com/102720472/188673178-3588b60d-c2c1-4264-a055-ffe03b1c2deb.png)

  
  - 본인 댓글만 삭제 가능
 
  
</details>

 # 프로젝트에서 기억남는 점 
  - JPA Audit
    - 데이터베이스에서 생성일, 수정일은 중요한 데이터로 공통적으로 많이 들어가게 된다
    - JPA Audit 기능을 통해서 도메인을 영속성 컨텍스트에 저장하거나 업데이트 하는 작업의 중복을 제거
    - <a href= "https://github.com/JUNHWAN96/azt/blob/bc836242ec39f5f3282463df35ca4491503917ec/src/main/java/com/example/azt/domain/AuditingFields.java#L13">코드확인</a>


