package com.ecommerce.project.controller;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.project.model.Category;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/api")
@RestController
public class CategoryController {
	CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/public/categories")
	public ResponseEntity<CategoryResponse> getAllCategories() {
		CategoryResponse categoryResponse = categoryService.getAllCategories();
		return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
	}
	@PostMapping("/public/categories")
	public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
		categoryService.createCategory(category);
		return new ResponseEntity<>("Category created successfully", HttpStatus.CREATED);
	}
	@PutMapping("/public/categories/{categoryId}")
	public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryId) {
		try {
			Category savedCategory = categoryService.updateCategory(category, categoryId);
			return new ResponseEntity<>("Category with category id: " + categoryId, HttpStatus.OK);
		}
		catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
	}
	@DeleteMapping("/admin/categories/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
		try {
			String status = categoryService.deleteCategoty(categoryId);
			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
	}
}
