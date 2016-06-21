package jkml.regex;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RegExServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RegExService regexServ = new RegExService();
		
		Request regexReq = new Request();
		
		regexReq.input = req.getParameter("input");
		regexReq.regex = req.getParameter("regex");
		
		
		Response regexRsp = regexServ.evaluate(regexReq);
		
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		String json = gson.toJson(regexRsp, Response.class);

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
	}


}
