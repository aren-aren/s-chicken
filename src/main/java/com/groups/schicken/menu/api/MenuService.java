package com.groups.schicken.menu.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuDAO menuDAO;

    public List<MenuVO> getMenus() {
        return menuDAO.getMenus();
    }

    public CategoryVO getMenus(String categoryId) {
        return menuDAO.getMenus(categoryId);
    }

    public List<CategoryVO> getCategories() {
        return menuDAO.getCategories();
    }

    @Transactional
    public CategoryVO setCategory(CategoryVO categoryVO) {
        int result = menuDAO.insertCategory(categoryVO);

        if(result == 0) throw new RuntimeException("insert 실패");

        return menuDAO.getCategory(categoryVO.getId());
    }

    public MenuVO getMenu(String menuId) {
        return menuDAO.getMenu(menuId);
    }
}
