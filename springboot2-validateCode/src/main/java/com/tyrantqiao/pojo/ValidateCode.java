package com.tyrantqiao.pojo;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
/**
 * @author tyrantqiao [tyrantqiao@gmail.com]
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
public class ValidateCode {
	private BufferedImage validateCodeImage;
	private String code;
	private LocalDateTime expireTime;

	public ValidateCode(BufferedImage validateCodeImage, String code, LocalDateTime expireTime) {
		this.validateCodeImage = validateCodeImage;
		this.code = code;
		this.expireTime = expireTime;
	}

	/**
	 * @param validateCodeImage
	 * @param code
	 * @param expireIn          过期时间，但是具体如何实现有待考察//TODO
	 */
	public ValidateCode(BufferedImage validateCodeImage, String code, int expireIn) {
		this.validateCodeImage = validateCodeImage;
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}

	/**
	 * 若现在时间>设定时间，则为失效的验证码
	 *
	 * @return
	 */
	public boolean validateCodeExpired() {
		return LocalDateTime.now().isAfter(expireTime);
	}

	public BufferedImage getValidateCodeImage() {
		return validateCodeImage;
	}

	public void setValidateCodeImage(BufferedImage validateCodeImage) {
		this.validateCodeImage = validateCodeImage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}
}