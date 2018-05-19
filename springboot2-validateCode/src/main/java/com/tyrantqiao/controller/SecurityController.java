package com.tyrantqiao.controller;

import com.tyrantqiao.pojo.Result;
import com.tyrantqiao.service.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tyrantqiao [tyrantqiao@gmail.com]
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
@RestController
public class SecurityController {
	private final Logger logger = LoggerFactory.getLogger(SecurityController.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private RequestCache requestCache = new HttpSessionRequestCache();
	private SecurityProperties securityProperties;
	private ResultService resultService;

	@Autowired
	public SecurityController(ResultService resultService) {
		this.resultService = resultService;
	}

	public Result requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			logger.info("redirect to:" + targetUrl);

			if (StringUtils.endsWith(targetUrl, "html")) {
				String redirectUrl = "login.html";
				redirectStrategy.sendRedirect(request, response, redirectUrl);
			}
		}
		return resultService.error(500, "need login");
	}
}
