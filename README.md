# earth
글로벌 아이티 - 최종 프로젝트(Model2) 어뜨

views/adminmypage/adminUser.jsp가 기본적인 뷰페이지이며, 현재 스타일이 적용되지 않아 검수중임.

src/admin/dao, controller, service 외 손대지않음

user-context.xml에 다음을 
<context:component-scan base-package="earth.admin.controller"/>
	<context:component-scan base-package="earth.admin.dao"/>
	<context:component-scan base-package="earth.admin.service"/>
	
