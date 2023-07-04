   
# 프로젝트명
> team 단체티  
> project : FireWorks   

[![NPM Version][npm-image]][npm-url]
[![Build Status][travis-image]][travis-url]
[![Downloads Stats][npm-downloads]][npm-url]

> 주제 : 전자결재 그룹웨어 프로그램 설계 

![](../header.png)


## 개발 환경 설정

모든 개발 의존성 설치 방법과 자동 테스트 슈트 실행 방법을 운영체제 별로 작성합니다.

```sh
make install
npm test
```  

## 사용 예제

>테스트 계정  
  사원 - id : fireworks  
     pw : qwer1234   
> 관리자 - id : qwe   
     pw : 1234 

## Collaborator

### 조원 : 오상현 <a href="https://github.com/yangchanyong">GitHub Link</a> <br>
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

### 조원 : 방한솔 <a href="https://github.com/yangchanyong">GitHub Link</a> <br>
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



### 조원 : 우성준 <a href="https://github.com/jooneei17">GitHub Link</a> <br>
#### 담당 : 회원근태, 결재선관리, 게시판(공지사항)관리
<details>
  <summary>내용</summary>
  <pre>
회원근태 : 

결재선 :

게시판 :
  </pre>
</details>



### 조원 : 이지윤 <a href="https://github.com/jooneei17">GitHub Link</a> <br>
#### 담당 : 회원근태, 결재선관리, 게시판(공지사항)관리
<details>
  <summary>내용</summary>
  <pre>
회원근태 : 

결재선 : 

게시판 : 
  </pre>
</details>


  

  




<!-- Markdown link & img dfn's -->
[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
[wiki]: https://github.com/yourname/yourproject/wiki
