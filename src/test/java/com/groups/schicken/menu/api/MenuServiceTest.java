package com.groups.schicken.menu.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.include=dev,prod")
@Transactional
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

    @Test
    void getMenuAndCategory(){
        String id = "1";

        CategoryVO categoryVO = service.getMenus(id);

        assertNotNull(categoryVO);
        assertNotNull(categoryVO.getMenus());
        assertNotEquals(categoryVO.getMenus().size(), 0);
    }
}
