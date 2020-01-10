package com.aditya.demof;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aditya.demof.controllers.HelloController;
import com.aditya.demof.controllers.ProductController;
import com.aditya.demof.models.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1ApplicationTests {

	@Autowired private HelloController hello;
	@Autowired private ProductController product;
	
	@Test
	public void contextLoads() {
		String result=hello.sayHello();
		assertEquals("Hello World",result);
	}
	@Test
	public void testProduct()
	{
		Product p= product.find(101);
		assertNotNull(p);
	}

}
