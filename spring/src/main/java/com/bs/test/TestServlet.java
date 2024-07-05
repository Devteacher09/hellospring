package com.bs.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Servlet implementation class TestServlet
 */

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String[] xml=getServletContext().getInitParameter("contextConfigLocation").split("\n");
		System.out.println(xml);
		
		String path=request.getRealPath("/");
		System.out.println(path);
		ApplicationContext context=new FileSystemXmlApplicationContext(path+xml[0]);
		for(String bean : context.getBeanDefinitionNames()) {
			System.out.println(bean);
			System.out.println(context.getBean(bean));
		}
		
		
		String servletXml=getInitParameter("contextConfigLocation");
		System.out.println(servletXml);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
