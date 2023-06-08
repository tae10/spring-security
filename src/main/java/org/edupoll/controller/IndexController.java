package org.edupoll.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {

	@GetMapping("/")
	public String indexHandle(HttpSession session,Model model) {
	Authentication auth =	SecurityContextHolder.getContext().getAuthentication();
	//인증을 안받으면 null 이 뜨긴함
	UserDetails userDetails = (UserDetails)auth.getPrincipal();
	System.out.println(userDetails);
		model.addAttribute("greet", "힘쎄고 강한아침");
		System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT"));
		return "index";
	}
	@GetMapping("/status")
	@ResponseBody
	public Object statusHandle(HttpSession session) {
		return session.getAttribute("SPRING_SECURITY_CONTEXT");
	}
	
	@GetMapping("/my-home")
	public String  showMyHome(@AuthenticationPrincipal UserDetails userDetails,Model model) {
		
		System.out.println(userDetails.getUsername());
		model.addAttribute("greet", "힘쎄고 왈도");
		
		return "index";
	}
	
}
