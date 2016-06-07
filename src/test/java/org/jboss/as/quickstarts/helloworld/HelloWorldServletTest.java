package org.jboss.as.quickstarts.helloworld;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.as.quickstarts.helloworld.HelloService;
import org.jboss.as.quickstarts.helloworld.HelloWorldServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@PrepareForTest({HelloService.class})
@RunWith(PowerMockRunner.class)
public class HelloWorldServletTest extends PowerMockito {
	@Mock
	HttpServletRequest req;
	
	@Mock
	HttpServletResponse resp;
		
	@Test
	public void testDoGet() throws IOException, ServletException {
		StringWriter sw = new StringWriter();
		PrintWriter pw  = new PrintWriter(sw);
		
		when(resp.getWriter()).thenReturn(pw);
		when(req.getParameter("name")).thenReturn("Michael");
		
		HelloWorldServlet helloServlet = new HelloWorldServlet();
		HelloService mockHelloService = PowerMockito.mock(HelloService.class);
		helloServlet.helloService = mockHelloService;
		
		when(mockHelloService.createHelloMessage("Mathius")).thenReturn("Hello Mathius !");
		when(mockHelloService.createHelloMessage("Michael")).thenReturn("Hello Michael !");
		
		helloServlet.doGet(req, resp);
		
		String result = sw.getBuffer().toString();
		
		assert(result.contains("Hello Michael !"));
	}
}
