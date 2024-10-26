package com.team2.mapper;

import com.team2.dto.CategoryDTO;
import com.team2.model.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

// Clase que se encarga de mapear entre la entidad Category y el DTO CategoryDTO
@Component
public class CategoryMapper {

    private final ModelMapper modelMapper;

    // Constructor que inyecta ModelMapper, usado para la conversión entre objetos
    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Metodo para convertir una entidad Category a su representación DTO
    public CategoryDTO toDto(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    // Metodo para convertir un DTO CategoryDTO a su representación de entidad
    public Category toEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }
}