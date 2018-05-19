package com.tyrantqiao.filter;

import com.tyrantqiao.pojo.ValidateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link OncePerRequestFilter}为单个Request设置的过滤器，而{@link InitializingBean}用于初始化bean，适用于要检索全文的时候【这个暂定是这样//TODO】
 *
 * @author tyrantqiao [tyrantqiao@gmail.com]
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
//@Component
public class ValidateFilter extends OncePerRequestFilter implements InitializingBean {
	private final Logger LOGGER = LoggerFactory.getLogger(ValidateFilter.class);
	//	private SessionStrategy sessionStrategy = new HttpSessionStrategy();
	private Set<String> validateUrls = new HashSet<>();
	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		validateUrls.add("/user/login");
		validateUrls.add("/user/register");
	}

	/**
	 * {@link HttpServletRequest#getRequestURI()}获得从协议名称到查询字符串的URL部分
	 *
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		boolean needInterceptor = false;
		for (String url : validateUrls) {
			if (antPathMatcher.match(url, request.getRequestURI())) {
				needInterceptor = true;
			}
			if (needInterceptor) {
				logger.info("Starting Interceptor");
				validate(request);
			}
		}
	}

	private void validate(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		ValidateCode ValidateCode = (ValidateCode) httpSession.getAttribute("ValidateCode");
		String validateCodeStr= ValidateCode.getCode();
//		if(request.get)
	}
}
