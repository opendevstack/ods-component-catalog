package com.boehringer.componentcatalog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "testing")
class ComponentCatalogApplicationTests {

	@Test
	void contextLoads() {
		// Empty, test will fail only if Spring Boot context load also fails
	}

}
