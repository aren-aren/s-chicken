package com.groups.schicken.menu.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.include=dev,prod")
class MenuServiceTest {

    @Autowired
    MenuService service;

    @Test
    void setCategory() {
        String name = "test Category";
        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setName(name);

        var created = service.setCategory(categoryVO);
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals(created.getName(), name);
    }
}
