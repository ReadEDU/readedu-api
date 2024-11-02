package com.team2.service;


import com.team2.dto.category.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
// Interfaz que define los métodos del servicio para manejar categorías
public interface AdminCategoryService {
    List<CategoryDTO> getAll();
    Page<CategoryDTO> paginate(Pageable pageable);
    CategoryDTO findById(Integer id);
    CategoryDTO create(CategoryDTO categoryDTO);
    CategoryDTO update(Integer id, CategoryDTO updateCategoryDTO);
    void delete(Integer id);
}
