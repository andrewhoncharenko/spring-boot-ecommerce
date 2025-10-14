package com.ecommerce.project.controller;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.project.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.project.model.Category;

@RestController
public class CategoryController {
	CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/api/public/categories")
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}
	@PostMapping("/api/public/categories")
	public String createCategory(@RequestBody Category category) {
		categoryService.createCategory(category);
		return "Category created successfully";
	}
	@DeleteMapping("/api/admin/categories/{categoryId}")
	public String deleteCategory(@PathVariable Long categoryId) {
		String status = categoryService.deleteCategoty(categoryId);
		return  status;
	}
}
