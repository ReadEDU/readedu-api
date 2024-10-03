package com.team2.mapper;

import com.team2.dto.author.AuthorDTO;
import com.team2.model.entity.Author;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    private final ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AuthorDTO toDTO(Author author){
        return modelMapper.map(author, AuthorDTO.class);
    }

    public Author toEntity(AuthorDTO authorDTO){
        return modelMapper.map(authorDTO, Author.class);
    }
}
