package web_proj.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_proj.dto.ActionForward;

public interface Action {
//execute 메서드로 오버라이딩 해야함.
	ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
