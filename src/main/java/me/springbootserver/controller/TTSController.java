package me.springbootserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TTSController {
	@RequestMapping("/hello")
	public String index() {
		return "Hello World";
	}
}
