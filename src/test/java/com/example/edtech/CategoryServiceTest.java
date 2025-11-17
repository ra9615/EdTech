package com.example.edtech;

import com.example.edtech.model.Category;
import com.example.edtech.repository.CategoryRepository;
import com.example.edtech.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void findByName_ExistingName_ReturnsCategory() {
        Category category = new Category();
        category.setName("Programming");
        when(categoryRepository.findByName("Programming")).thenReturn(Optional.of(category));

        Optional<Category> result = categoryService.findByName("Programming");

        assertTrue(result.isPresent());
        Assertions.assertEquals("Programming", result.get().getName());
    }
}