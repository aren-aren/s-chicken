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
      , c.name AS category
    FROM menu_and_category mac
        INNER JOIN menu m ON m.id = mac.menu_id
        INNER JOIN menu_category c ON c.id = mac.menu_category
    """)
    List<MenuVO> getMenus();

    @Select("""
    SELECT
        id
      , name
    FROM  menu_category
    WHERE id=#{categoryId}
    """)
    @Results(id="getCategoryVO", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "id", property = "menus", many = @Many(select = "com.groups.schicken.menu.api.MenuDAO.getCategoryMenus"))
    })
    CategoryVO getMenusASCategoryVO(String categoryId);

    @Select("""
    SELECT
          id
        , menu
        , price
    FROM menu m
        INNER JOIN menu_and_category mac ON mac.menu_id = m.id
    WHERE mac.menu_category = #{categoryId}
    """)
    List<MenuVO> getCategoryMenus(String categoryId);

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
    WHERE id = #{categoryId}
    """)
    CategoryVO getCategory(String categoryId);

    @Select("""
    SELECT id, menu, price
    FROM menu
    WHERE id = #{menuId}
    """)
    MenuVO getMenu(String menuId);

    @InsertProvider(MenuAPISqlProvider.class)
    int setMenuAndCategory(String categoryId, String[] menuIds);
}
