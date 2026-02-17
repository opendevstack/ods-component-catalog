package org.opendevstack.component_catalog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "testing")
class ProjectComponentCatalogApplicationTests {

	@Test
	void contextLoads() {
		// Empty, test will fail only if Spring Boot context load also fails
	}

}
