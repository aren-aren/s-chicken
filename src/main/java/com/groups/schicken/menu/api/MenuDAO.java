package com.groups.schicken.menu.api;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuDAO {
    @Select("""
    SELECT 
        m.id
      , m.menu
      , m.price
      , c.name
    FROM menu_and_category mac
        INNER JOIN menu m ON m.id = mac.menu_id
        INNER JOIN menu_category c ON c.id = mac.menu_category
    """)
    List<MenuVO> getMenus();

    @Select("""
    SELECT id, name
    FROM menu_category
    """)
    List<CategoryVO> getCategories();

    @Insert("""
    INSERT INTO menu_category(name)
    VALUES (#{name})
    """)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertCategory(CategoryVO categoryVO);

    @Select(value = """
    SELECT id, name
    FROM menu_category
    WHERE id = #{id}
    """)
    CategoryVO getMenu(String id);
}
