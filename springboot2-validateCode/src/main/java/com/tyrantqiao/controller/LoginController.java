package com.tyrantqiao.controller;

import com.tyrantqiao.entity.User;
import com.tyrantqiao.pojo.Result;
import com.tyrantqiao.service.ResultService;
import com.tyrantqiao.util.ValidateCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author tyrantqiao [tyrantqiao@gmail.com]
 */
@RestController
public class LoginController {
	private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	private ResultService resultService;
	private ValidateCodeService validateCodeService;

	@Autowired
	public LoginController(ResultService resultService, ValidateCodeService validateCodeService) {
		this.resultService = resultService;
		this.validateCodeService = validateCodeService;
	}

	@GetMapping(value = "/user/login")
	public ModelAndView home(ModelAndView modelAndView) {
		modelAndView.setViewName("login");
		return modelAndView;
	}

	/**
	 * 因为validateCode 不属于User类，所以用{@code @ModelAttribute}提取对应的元素
	 * @param request
	 * @param response
	 * @param inputValidateCode
	 * @return
	 */
	@PostMapping(value = "/user/login")
	public Result<User> processLoginResult(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("validateCode") String inputValidateCode, @Valid User user) {
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("validateCode");
		LOGGER.info("input code is " + inputValidateCode);
		if (StringUtils.equalsIgnoreCase(inputValidateCode, code)) {
			return resultService.success(user,"pass the validate code");
		}
		return resultService.error(500, "validate code error");
	}
}
