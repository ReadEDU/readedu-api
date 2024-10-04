package com.team2.service.impl;


import com.team2.dto.CategoryDTO;
import com.team2.exception.BadRequestException;
import com.team2.exception.ResourceNotFoundException;
import com.team2.mapper.CategoryMapper;
import com.team2.model.entity.Category;
import com.team2.repository.CategoryRepository;
import com.team2.service.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminCategoryServiceImpl implements AdminCategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CategoryDTO> paginate(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::toDto);
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDTO findById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría con ID " + id + " no fue encontrada"));
        return categoryMapper.toDto(category);
    }

    @Transactional
    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        // Verificar si ya existe una categoría con el mismo nombre
        categoryRepository.findByName(categoryDTO.getName())
                .ifPresent(existingCategory -> {
                    throw new BadRequestException("La categoría ya existe con el mismo nombre");
                });

        Category category = categoryMapper.toEntity(categoryDTO);
        category.setCreatedAt(LocalDateTime.now());
        category = categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }


    @Transactional
    @Override
    public CategoryDTO update(Integer id, CategoryDTO updateCategoryDTO) {
        Category categoryFromDb = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría con ID " + id + " no fue encontrada"));

        // Verificar si ya existe otra categoría con el mismo nombre
        categoryRepository.findByName(updateCategoryDTO.getName())
                .filter(existingCategory -> !existingCategory.getId().equals(id))
                .ifPresent(existingCategory -> {
                    throw new BadRequestException("Ya existe otra categoría con el mismo nombre");
                });

        categoryFromDb.setName(updateCategoryDTO.getName());
        categoryFromDb.setDescription(updateCategoryDTO.getDescription());
        categoryFromDb.setUpdatedAt(LocalDateTime.now());

        categoryFromDb = categoryRepository.save(categoryFromDb);
        return categoryMapper.toDto(categoryFromDb);
    }


    @Transactional
    @Override
    public void delete(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría con ID " + id + " no fue encontrada"));
        categoryRepository.delete(category);
    }
}
