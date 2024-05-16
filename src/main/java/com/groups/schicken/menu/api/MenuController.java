package com.groups.schicken.menu.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/")
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    private final MenuService service;

    @GetMapping("menus/")
    public ResponseEntity<List<MenuVO>> getMenus(){
        List<MenuVO> menus = service.getMenus();

        if(menus == null || menus.isEmpty()){
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(menus);
    }

    @GetMapping("categories/")
    public ResponseEntity<List<CategoryVO>> getCategories(){
        List<CategoryVO> categories = service.getCategories();

        if(categories == null || categories.isEmpty()){
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(categories);
    }

    @PostMapping("categories/")
    public ResponseEntity<CategoryVO> setCategory(CategoryVO categoryVO){
        try {
            CategoryVO created = service.setCategory(categoryVO);
            if(created == null){
                ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok(created);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
