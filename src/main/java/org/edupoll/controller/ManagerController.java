package org.edupoll.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {

	@GetMapping("/manager")
	public String managerHandle() {

		return "manager/index";
	}

	@GetMapping("/manager/report")
	public String managerReportHandle() {

		return "manager/report";
	}
}
