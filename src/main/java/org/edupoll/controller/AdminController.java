package org.edupoll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin")
	public String showAdminHome() {

		return "admin/index";
	}

	@GetMapping("/admin/notice")
	public String showAdminNotice() {

		return "admin/notice";
	}
}
