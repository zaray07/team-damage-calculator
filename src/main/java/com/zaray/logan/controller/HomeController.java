package com.zaray.logan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.zaray.logan.domain.ServerLog;
import com.zaray.logan.service.ServerLogService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private ServerLogService serverLogService;

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(Model model) {
		ServerLog serverLog = new ServerLog();
		model.addAttribute("formAttributeServerLog", serverLog);
		return "home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute ServerLog serverLog, Model model) {
		model.addAttribute("serverLog", serverLog);
		serverLogService.doTextAnalize(serverLog);
		model.addAttribute("players", serverLog.getListOfPlayers());
		return "success";
	}

}
