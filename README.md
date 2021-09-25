# EARTH 어뜨
  - 글로벌 아이티 - 최종 프로젝트(Model.2) 어뜨
  - 환경보호의 중요성을 대중 전파하고 이용자들 간 환경을 보호하는 활동을 공유할 수 있는 커뮤니티
  
## 팀원 및 담당분야
  - 김하영 (조장) : 어뜨, 프론트엔드, 행사일정, 꿀팁, 환경일지 
  - 최홍재 : 어드민마이페이지
  - 노현호 : 공지사항 게시판, 오늘의 실천 게시판
  - 김예찬 : 뱃지샵
  - 이다희 : 이달의 챌린지
  - 이영인 : 회원가입, 로그인, 유저마이페이지

## 사용 기술
  - FrontEnd
    HTML / CSS / JavaScript / ajax
    
  - BackEnd
    JDK 1.8
    
  - DataBase
    Oracle 1.9c
    
  - 공통
    Github.com
   
## 개발 환경
  - Eclipse IDE
  - Oracle SQL Develpoer
  
  
<이하 홍재 작성>
# earth
글로벌 아이티 - 최종 프로젝트(Model2) 어뜨

adminUser에서 삭제기능을 추가중

adminQuestion에서 1:1문의 답변하기 폼을 작성중

현재 추가된 뷰 9/24

adminBoard
adminComment
adminModify
adminNotice
adminQuestion
adminUser

src/admin/dao, controller, service 외 손대지않음

user-context.xml에 다음을 추가해야함

<context:component-scan base-package="earth.admin.controller"/>
<context:component-scan base-package="earth.admin.dao"/>
<context:component-scan base-package="earth.admin.service"/>
* DTO 중 UserDTO는 user.dto에 있는것을 사용함.
