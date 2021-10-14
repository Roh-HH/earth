# EARTH 어뜨
  - 글로벌 아이티 - 최종 프로젝트(Model.2) 어뜨
  - 환경보호의 중요성을 대중 전파하고 이용자들 간 환경을 보호하는 활동을 공유할 수 있는 커뮤니티

## 사용 기술
  - FrontEnd
    - HTML / CSS / JavaScript / ajax 
    
  - BackEnd
    - JDK 1.8
    
  - DataBase
    - Oracle 1.9c
   
## 개발 환경
  - Eclipse IDE
  - Oracle SQL Develpoer
  
## 종강 후 추가 업데이트
  - (21.10.04) 쪽지-휴지통 기능 추가
    - 모든 유저는 받은 쪽지/보관된 쪽지를 삭제했을 때 삭제한 쪽지를 휴지통에서 확인할 수 있습니다
    - 휴지통에서는 개별 쪽지에 대해 복구 또는 신고를 할 수 있으며 휴지통 비우기 버튼을 통해 휴지통의 쪽지를 전부 삭제할 수 있습니다
    - 추가된 파일 : WebContent/WEB-INF/views/main/messageDeleteList.jsp
  - (21.10.08) 회원가입-휴대폰 본인인증 기능 추가
    - 회원가입 시 입력한 전화번호로 본인인증을 진행합니다
    - 인증이 완료되지 않을 경우 회원가입이 불가합니다
    - 인증코드는 6자리 숫자로 무작위로 생성되어 문자로 전송됩니다
    - 인증코드는 메시지 전송 후 3분 뒤 자동으로 폐기되어 사용할 수 없게 처리됩니다
