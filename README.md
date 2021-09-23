# earth
글로벌 아이티 - 최종 프로젝트(Model2) 어뜨

adminUser에서 삭제기능을 추가중

adminQuestion에서 1:1문의 답변하기 폼을 작성중

현재 추가된 뷰 9/23

adminUser
adminModify
adminBoard
adminComment
adminQuestion

src/admin/dao, controller, service 외 손대지않음

user-context.xml에 다음을 추가해야함

<context:component-scan base-package="earth.admin.controller"/>
<context:component-scan base-package="earth.admin.dao"/>
<context:component-scan base-package="earth.admin.service"/>
* DTO 중 UserDTO는 user.dto에 있는것을 사용함.
