package earth.board.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class EarthAspect {
	
	@Around("execution(* et*(..))")
	public Object around(ProceedingJoinPoint j) throws Throwable {
		
		// AOP 에서 MVC Httprequest 	객체 꺼내기
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		ServletRequestAttributes sa = (ServletRequestAttributes)ra;
		HttpServletRequest request = sa.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("sid") == null) {
			//로그아웃 상태
			System.out.println("로그아웃입니다. 로그인하고 이용해주세요");
			
			return "user/loginForm";
			
			}
		System.out.println("!!!!!!!!!!AOP!!!!!!!!");
		
		return j.proceed(); // 로그인상태일때 가던길 가라 ~ 
	}
	
 

}
