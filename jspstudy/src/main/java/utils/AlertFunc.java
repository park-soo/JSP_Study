package utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

public class AlertFunc {
	public static void alertLocation(String msg, String url, JspWriter out) {
		String script = "<script>" + "alert('"+msg+"');" + "location.href='"+url+"';" + "</script>";
		try {
			out.println(script);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void alertBack(HttpServletResponse resp, String msg) {
		try {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		String script = "<script>" + "alert('"+msg+"');"+ "history.back();" + "</script>";
			writer.println(script);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
