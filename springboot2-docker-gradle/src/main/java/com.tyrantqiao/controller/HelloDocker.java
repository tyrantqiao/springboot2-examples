package com.tyrantqiao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tyrantqiao [tyrantqiao@gmail.com]
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
@RestController
public class HelloDocker {
	@GetMapping("/")
	public String hello(){
		return "hello docker by gradle";
	}
}
