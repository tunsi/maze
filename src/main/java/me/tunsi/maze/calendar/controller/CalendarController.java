package me.tunsi.maze.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

	@RequestMapping("/query")
	@ResponseBody
	public String query() {
		return "abc";
	}
}
