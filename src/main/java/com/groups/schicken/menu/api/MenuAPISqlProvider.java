package com.groups.schicken.menu.api;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class MenuAPISqlProvider implements ProviderMethodResolver {
    public static String setMenuAndCategory(String categoryId, String[] menuIds){
        return new SQL(){{
            INSERT_INTO("menu_and_category");
            INTO_COLUMNS("menu_category", "menu_id");
            for (String menuId : menuIds) {
                INTO_VALUES(categoryId, menuId);
                ADD_ROW();
            }
        }}.toString();
    }
}
