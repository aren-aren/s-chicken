package com.groups.schicken.menu.api;

import lombok.Data;

@Data
public class MenuIntoCategoryVO {
    private String categoryId;
    private String[] menuIds;
}
