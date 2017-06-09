package io.jky.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-renren.xml" })
public class TestGeneratorWord {
	@Autowired
	private ClassRegistrationService classRegistrationService;
	
	@Test
	public void testGen() {
		/*try {
			classRegistrationService.generatorWord("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
