package com.tyrantqiao.util;

import com.tyrantqiao.pojo.ValidateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author tyrantqiao [tyrantqiao@gmail.com]
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
@Service
public class ValidateCodeService {
	private static final String RANDOMCODEKEY = "VALIDATECODE";
	private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private int width = 95;
	private int height = 25;

	private static final Logger logger = LoggerFactory.getLogger(ValidateCodeService.class);

	private Random random = new Random();

	public ValidateCode getValidateImage() {
		BufferedImage validateImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = validateImage.getGraphics();
		graphics.fillRect(0, 0, width, height);
		graphics.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		graphics.setColor(getRandColor(110, 133));
		int lineSize = 40;
		for (int i = 0; i <= lineSize; i++) {
			drawLine(graphics);
		}
		String validateCode = "";
		int validateCodeLength = 4;
		for (int i = 1; i <= validateCodeLength; i++) {
			validateCode = drawString(graphics, validateCode, i);
		}
		logger.info("validate code is:" + validateCode);
		graphics.dispose();
		return new ValidateCode(validateImage, validateCode, 600);
	}

	/**
	 * 获得字体
	 */
	private Font getFont() {
		return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
	}

	/**
	 * 获得颜色
	 */
	private Color getRandColor(int fc, int bc) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc - 16);
		int g = fc + random.nextInt(bc - fc - 14);
		int b = fc + random.nextInt(bc - fc - 18);
		return new Color(r, g, b);
	}

	/**
	 * 绘制字符串
	 */
	private String drawString(Graphics g, String randomString, int i) {
		g.setFont(getFont());
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
		String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
		randomString += rand;
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 13 * i, 16);
		return randomString;
	}

	/**
	 * 绘制干扰线
	 */
	private void drawLine(Graphics g) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(13);
		int yl = random.nextInt(15);
		g.drawLine(x, y, x + xl, y + yl);
	}

	/**
	 * 获取随机的字符
	 */
	public String getRandomString(int num) {
		return String.valueOf(randString.charAt(num));
	}
}
