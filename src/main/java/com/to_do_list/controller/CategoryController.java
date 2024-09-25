package com.to_do_list.controller;

import com.to_do_list.DTO.CategoryDTO;
import com.to_do_list.DTO.TaskDTO;
import com.to_do_list.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
   public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
   }
   @GetMapping("/{id}")
   public ResponseEntity<CategoryDTO> findCategoryById(Long id){
        CategoryDTO category = categoryService.findCategoryById(id);
        return ResponseEntity.ok(category);
   }
    @PostMapping
   public ResponseEntity<CategoryDTO> addCategory (@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO newCategory = categoryService.addCategory(categoryDTO);
        URI location = URI.create(String.format("/category%d", newCategory.getId()));
        return ResponseEntity.created(location).body(newCategory);
   }

   @PutMapping("/{id}")
   public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody Long id, CategoryDTO categoryDTO){
     CategoryDTO updatedCategory = categoryService.updateCategory(id,categoryDTO);
     return ResponseEntity.ok(updatedCategory);
   }
    @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteCategory (@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
   }
}
