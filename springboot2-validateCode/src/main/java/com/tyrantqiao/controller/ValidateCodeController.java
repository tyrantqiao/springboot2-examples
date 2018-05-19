package com.tyrantqiao.controller;

import com.tyrantqiao.pojo.ValidateCode;
import com.tyrantqiao.util.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author tyrantqiao [tyrantqiao@gmail.com]
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
@RestController
public class ValidateCodeController {
	private ValidateCodeService validateCodeService;
	private String validateKey="validateCode";

	@Autowired
	public ValidateCodeController(ValidateCodeService validateCodeService) {
		this.validateCodeService = validateCodeService;
	}

	@GetMapping("/image/validateCode")
	public void getValidateCode(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		ValidateCode validateCode = validateCodeService.getValidateImage();

		session.removeAttribute(validateKey);
		session.setAttribute(validateKey, validateCode.getCode());
		try{
			ImageIO.write(validateCode.getValidateCodeImage(),"JPEG",response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
