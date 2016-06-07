package org.jboss.as.quickstarts.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

import org.jboss.as.quickstarts.helloworld.HelloService;


@PrepareForTest({HelloService.class})
@RunWith(PowerMockRunner.class)
public class HelloServiceTest extends PowerMockito {
	@Mock
	HelloService hello;
	
	@Test
	public void testCreateHelloMessage() {
		HelloService hello = PowerMockito.mock(HelloService.class);
		
		when(hello.createHelloMessage("Mathius")).thenReturn("Hello Mathius !");
		when(hello.createHelloMessage("")).thenReturn("Hello !");
		
		assertEquals("Hello Mathius !", hello.createHelloMessage("Mathius"));
	}
}
