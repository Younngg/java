package org.zerock.security.security.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Custom403Handler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws
		IOException, ServletException {
		log.info("Access denied");
		response.setStatus(HttpStatus.FORBIDDEN.value());

		String contentType = request.getHeader("Content-Type");
		boolean jsonRequest = contentType.startsWith("application/json");

		log.info("isJSON: " + jsonRequest);

		if (!jsonRequest) {
			response.sendRedirect("/member/login?error=ACCESS_DENIED");
		}
	}

}
