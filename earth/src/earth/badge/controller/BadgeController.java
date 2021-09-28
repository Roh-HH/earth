package earth.badge.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import earth.badge.dto.BadgeDTO;
import earth.user.dto.MybagDTO;
import earth.badge.service.BadgeServiceImpl;


@Controller
@RequestMapping("/badge/*")
public class BadgeController {

	@Autowired
	private BadgeServiceImpl badgeService = null;

	// 뱃지샵 메인
	@RequestMapping("badgeShop.et")	
	public String badgeShop(String pageNum, Model model,HttpSession session,HttpServletRequest request) throws SQLException{
		System.out.println("뱃지샵 메인 페이지 요청");
		
		// 필터 넘어오는거 없으면 basic(최신순) 설정, 있으면 담아주기
		String filter = request.getParameter("filter");
		if(filter == null){
			filter = "basic";
		}
		
		
		Map<String, Object> result;
		
		// 뱃지 목록 가져오기 (필터 값있으면 필터 값 넘겨주기)
		result = badgeService.getBadgeList(pageNum,filter);

		
		// view 에 전달할 데이터 보내기
		model.addAttribute("pageSize", result.get("pageSize"));
		model.addAttribute("pageNum", result.get("pageNum"));
		model.addAttribute("currentPage", result.get("currentPage"));
		model.addAttribute("startRow", result.get("startRow"));
		model.addAttribute("endRow", result.get("endRow"));
		model.addAttribute("articleList", result.get("articleList"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("number", result.get("number"));
		model.addAttribute("filter", filter);

		// 세션에 sid 관리자 저장 (임시)
		String id = "admin";
		session.setAttribute("sid", id);
		
		return "badge/badgeShop";
	}

	
	// 뱃지 구매
	@RequestMapping("buyBadge.et")	
	public String buyBadge(int num, Model model,HttpSession session) throws SQLException{
		System.out.println("뱃지 구매 페이지 요청");
		
		// 세션에서 유저 아이디 불러오기 
		//String id = (String)session.getAttribute("sid");
		
		// 뱃지 하나 불러오기
		BadgeDTO result = badgeService.getBadge(num);
		
		// (임시) 유저 포인트 불러오기 (!!세션처리 되면 id로 받아오기) 
		String uid = "test01";
		int point = badgeService.getPoint(uid);
		
		// model로 넘겨주기
		model.addAttribute("result",result);
		model.addAttribute("point",point);
		
		// 세션에 sid 관리자 저장 (임시)
		String id = "admin";
		session.setAttribute("sid", id);
		
		return "badge/buyBadge";
	}

	// 뱃지 구매 완료
	@RequestMapping("buyComplete.et")	
	public String buyComplete(@RequestParam("num") int num, MybagDTO dto, Model model, HttpSession session) throws SQLException{
		System.out.println("뱃지 구매 완료 페이지 요청");

		
		// sid와 뱃지 고유번호 불러오기 ( 임시 )
		//dto.setId((String)session.getAttribute("sid"));
		dto.setId("testuser");																				
		dto.setBadgenum(num);		
				
	
		// 포인트 차감 서비스
		
		
		// mybag에 컬럼 추가
		
		int result = badgeService.buyBadge(dto);
		System.out.println("구매 성공 여부 : " + result);
		// result = 0 실패 // result = 1 성공
		
		model.addAttribute("result",result);
		
		return "badge/buyComplete";
	}
	
	// 뱃지 추가하기 Form (어드민만)
	@RequestMapping("addBadgeForm.et")	
	public String addBadgeForm(Model model) throws SQLException{
		System.out.println("뱃지 추가 form 페이지 요청");

		// 관리자만 접속 가능
		
		
		
		return "badge/addBadgeForm";
	}

	// 뱃지 추가하기 Pro
	@RequestMapping("addBadgePro.et")	
	public String addBadgePro(BadgeDTO dto, MultipartHttpServletRequest request, Model model) throws SQLException, IOException{
		System.out.println("뱃지추가 pro 페이지 요청");
		
		try {
			// 파라미터 이름(form에서 전돨되는 name 속성값) 주며, 해당 파일 정보 변수에 담기
			MultipartFile mf = request.getFile("img");
			System.out.println(mf);
			
			// save 폴더 저장
			String path = request.getRealPath("save");
			System.out.println("badgeimg path : " + path);
			
			// UUID 사용 (중복회피)
			UUID uuid = UUID.randomUUID();
			System.out.println("UUID : " + uuid.toString().replace("-",""));
			
			String orgName = mf.getOriginalFilename();
			System.out.println("orgName : " + orgName);
			
			// 확장자를 뺀 파일이름 
			String imgName = orgName.substring(0, orgName.lastIndexOf('.'));
			System.out.println("imgName :" + imgName);
			
			//확장자 가져오기
			String ext = orgName.substring(orgName.lastIndexOf('.')); 
			System.out.println("ext : " +  ext);
			
			String newName = uuid.toString().replace("-", "") + ext;

			// 저장될 파일의 전체 경로 세팅 : save경로 + 새이미지 파일명
			String imgPath = path + "\\" + newName;
			System.out.println("imgPath : " + imgPath);

			
			File f = new File(imgPath); // 파일 객체 생성
			mf.transferTo(f); // 이미지 서버에 저장!
			
			System.out.println("newName : " + newName);
			dto.setBadgeimg(newName);
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		
		
		// 뱃지 추가하기
		badgeService.addBadge(dto);
		
		return "badge/addBadgePro";
	}
		
	
	// 뱃지 FAQ
	@RequestMapping("badgeFAQ.et")	
	public String badgeFAQ(String pageNum, String sel, String search, Model model) throws SQLException{
		System.out.println("뱃지샵 FAQ 페이지 요청");

		
		return "badge/badgeFAQ";
	}
}
