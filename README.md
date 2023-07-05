   
# 프로젝트명
> team 단체티  
> project : FireWorks   
> 주제 : 전자결재 그룹웨어 프로그램 설계

[![NPM Version][npm-image]][npm-url]
[![Build Status][travis-image]][travis-url]
[![Downloads Stats][npm-downloads]][npm-url]



![](../header.png)

## 개발 환경 설정


>Front-end : JQuery & AjAX, BootStrap4, CKEditor, {{ MUSTACHE }}, TreeView.js  
Back-end : JAVA 1.8, Spring Boot, Spring Security, MariaDB, JPA  
Tools : IntelliJ, DBeaver, Postman, Git
  

## 사용 예제

>테스트 계정  
  사원 - id : fireworks  
     pw : qwer1234   
> 관리자 - id : qwe   
     pw : 1234 

## Collaborator

### 조원 : 오상현 <a href="https://github.com/OhHalfmoon">GitHub Link</a> <br>
#### 담당 : 전자서명등록, 기안등록, 결재 반려/승인, 결재함/문서함
<details>
  <summary>내용</summary>
  <pre>
전자서명등록 : 전자서명이 등록되지 않으면 문서 확인페이지(get)에 접근할 수 없다.
js로 파일의 형식이 이미지파일인지 체크가 이루어진다.
등록된 서명을 인덱스 페이지에서 확인이 가능하며, 서명파일은 수정이 가능하지만, 
전자서명이 없으면 서비스 이용이 불가능하기 때문에 삭제기능은 이용할 수 없도록 하였다.

결재등록 : 문서양식을 선택하여 결재문서를 작성할 수 있다. 
이때 결재선이 없을경우 작성페이지에서 메인화면으로 리턴된다.
문서의 상단에서는 기본적인 문서정보(제목, 문서함, 결재선, 기안일자)를 입력한다.
문서 하단에서 구체적인 문서 작성이 가능하며, 워드파일(doc문서) 내용 붙혀넣기가 가능하다.
작성 후, 저장버튼과 임시저장버튼중 선택하여 선택이 가능하다.
저장버튼은 결재자에게 문서가 제출되고, 임시저장은 임시저장함으로 문서가 저장된다.

결재 반려/승인 : 결재요청이 올 경우, 페이지 상단 알림 또는 결재함의 '결재수신문서'를 통해 확인이 가능하다.
결재자 이외의 사원이 버튼을 누를경우 '권한이 없습니다' 라는 알림이 생성된다.
결재승인을 누를경우 문서 상단에 해당 결재자의 전자서명이 등록된다.
결재반려를 누를경우 문서 상단 모든 전자서명이 초기화되며, 문서는 임시저장 상태로 돌아간다.

ck에디터로 생성된 요소에는 id값 또는 class값을 부여할 수 없으며,
상위요소에서 dom탐색을 통한 변환시도 또한 허용되지 않았다.
이를 해결하기위해 실제 작성된 기안문서의 html이 DB에 문자열 타입으로 저장이 되는것을 확인하였으며,
특정 태그가 <img>태그로 replace되도록 설정하였다.
반려될경우, 같은 원리로 replaceAll과 정규식을 통해 초기화를 진행하였다.

  </pre>
</details>

### 조원 : 방한솔 <a href="https://github.com/hsnachos">GitHub Link</a> <br>
#### 담당 : 부서관리, 양식관리, 파일 업로드, 기타 공통 기능 구현
<details>
  <summary>내용</summary>
  <pre>
부서 관리

- 조직도에서 회사 내부의 부서 목록 및 부서별 사원 목록 조회 가능
  - 부서 혹은 사원을 선택시 선택한 항목의 상세 정보 조회가능
- 부서 추가 및 부서 수정, 삭제 기능
- 선택한 사원의 부서 변경 기능

결재 양식
- 결재양식 추가 및 상세 조회, 수정, 삭제기능
- 목록에서는 기본적으로 10개씩 출력하도록 페이징 처리가 되어있음. 
- 결재양식을 작성 시 사용여부를 선택할 수 있으며 미사용을 선택했을 경우엔 결재 등록시 양식이 뜨지 않고 수정을 통해 사용여부 및 내용을 변경 할 수 있다. 
- 양식명, 양식 내용, 양식명 + 양식 내용 카테고리로 검색가능

파일 업로드
- ck에디터 이미지 파일 업로드 가능
- 결재는 단일 업로드, 공지 게시판은 다중 업로드로 되어있음
  - 추후 결재를 다중 업로드로 변경할 예정
- 이미지 조회 및 파일 다운로드 API 분리

기타 공통기능
- Auditing을 활용한 생성일자, 수정일자 자동화 
- JPA 조회 API 구현(Pagination, Specification)
    </pre>
</details>



### 조원 : 양찬용 <a href="https://github.com/yangchanyong">GitHub Link</a> <br>
#### 담당 : 회원 - security 적용, 일반회원, 관리자
<details>
  <summary>내용</summary>
  <pre>
회원 : 회사에서 부여된 부서, 직급 등 회사정보, 개인정보를 입력하여 회원가입을 한다.
우측 상단 탭에서 회원정보, 근태를 확인할 수 있으며
회원정보탭에서는 회원의 정보, 수정, pw 수정을 할 수 있다.
근태는 최근 30일 근태만 출력된다.

 권한 : fireworks에는 권한이 role, state, manager 3개의 컬럼으로 관리된다.
role은 총 4개의 레벨로 이루어져 있고 사원, 팀장, 사장, 탈퇴회원으로 관리된다.
state는 3개의 레벨로 미승인유저, 승인유저, 탈퇴회원으로 관리된다.
매니저는 총 2개의 레벨로 0(일반회원), 1(매니저)가 있다.
  
관리자 : 관리자 메뉴는 양식관리, 사원관리, 부서관리가 있다.
사원관리 : 회원을 승인하고, 회원 권한을 수정할 수 있으며 퇴근시간을 직접 입력할 수 있다.
  </pre>
</details>



### 조원 : 우성준 <a href="https://github.com/udyr-woo">GitHub Link</a> <br>
#### 담당 : 알림, 쪽지
<details>
  <summary>내용</summary>
  <pre>
    알림: 결재에 관한 요청, 반려 혹은 완료처리가 되었을 때와 신규 공지사항, 공지사항 내용이 수정 되었을 때, 알림이 보내지게 됩니다. 
         읽지 않은 알림은 위에 숫자가 표시 되며, 알림 클릭시 해당 페이지로 이동하게 되고, 읽은 알림은 자동으로 삭제가 됩니다.
    쪽지: 쪽지는 받은 쪽지와 보낸 쪽지로 구분되며,  특별히, 보낸 쪽지에는 수신여부 확인이 가능합니다. 
         쪽지 리스트에 있는 쪽지 중 하나를 클릭하면 쪽지 내용을 확인 할 수 있습니다. 또한 체크박스로 선택하고 삭제할 수도 있습니다. 
         쪽지 작성시에는 제목, 수신자, 내용을 필수적으로 작성해야하고, 수신자는 개인 혹은 다수로 설정이 가능합니다.
    사이트: https://final.bluetea.cloud
  </pre>
</details>



### 조원 : 이지윤 <a href="https://github.com/jooneei17">GitHub Link</a> <br>
#### 담당 : 회원근태, 결재선관리, 게시판(공지사항)관리
<details>
  <summary>내용</summary>
  <pre>
회원근태 : 로그인한 회원은 출퇴근 등록을 할 수 있다. 
재확인 버튼을 통해 출근과 퇴근이 등록되며 연속으로 입력되는 문제를 방지한다.
출근 등록 없이 퇴근을 할 수 없고, 퇴근 등록을 못 하였을 경우 관리자에게 문의한다.

결재선 : 로그인한 사용자를 기안자로 하여, 결재 선을 생성할 수 있다.
결재 선 생성 시에는 결재선 명과 결재자를 지정할  수 있으며,
결재자는 최소 한 명에서 최대 세 명까지 가능하다.
생성된 결재 선은 나의 결재선에 보여지게 되며, 단일 삭제만 가능하다.

게시판 : 팀장 이상의 권한을 가진 회원 만이 글쓰기 버튼이 보여지며,
공지사항 게시판의 글을 작성할 수 있다.
제목과 내용을 통한 검색이 가능하며, 자신이 작성한 글 만이 수정과 삭제가 가능하다.

  </pre>
</details>



## Document

>#### 서류 : <a href="https://docs.google.com/spreadsheets/d/1GRlM8714v9oGw3WbDz8jqPfoP7ULFIXCdVFrU4gGe-4/edit?usp=sharing">서류링크</a>
>#### 서류내용 : 요구사항 정의서, 테이블명세서, 업무분장   
>#### ppt : 파일첨부
  

  




<!-- Markdown link & img dfn's -->
[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
[wiki]: https://github.com/yourname/yourproject/wiki
