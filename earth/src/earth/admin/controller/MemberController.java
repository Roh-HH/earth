package earth.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	@GetMapping("signupForm.do")
	public String signupForm() {
		System.out.println("signupForm 요청");
		return "member/signupForm";
	}
}
