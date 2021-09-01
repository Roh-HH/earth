package earth.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/*")
public class UserController {
	@GetMapping("signupForm.do")
	public String signupForm() {
		System.out.println("signupForm 실행");
		return "member/signupForm";
	}
}
