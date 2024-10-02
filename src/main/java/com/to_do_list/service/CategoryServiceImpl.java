package com.to_do_list.service;

import com.to_do_list.DTO.CategoryDTO;
import com.to_do_list.entity.Category;
import com.to_do_list.exception.CategoryNotFoundException;
import com.to_do_list.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("The category "+ id +
                        " was not found\n૮ ◞ ﻌ ◟ ა"));
        return convertEntityToDTO(category);
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category newCategory = convertDTOToEntity(categoryDTO);
        Category savedCategory =categoryRepository.save(newCategory);
        return convertEntityToDTO(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category oldCategory = categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException ("The category "+ id
                        + " was not found \n૮꒰◞ ˕ ◟꒱ა"));
        oldCategory.setName(categoryDTO.getName());
        Category updatedCategory = categoryRepository.save(oldCategory);
        return convertEntityToDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("The category "+ id
                        + " was not found \n૮꒰◞ ˕ ◟꒱ა"));
        categoryRepository.delete(category);

    }

    public CategoryDTO convertEntityToDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }
    public Category convertDTOToEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }

}
