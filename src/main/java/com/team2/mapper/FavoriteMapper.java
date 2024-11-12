package com.team2.mapper;

import com.team2.dto.favorite.FavoriteCreateUpdateDTO;
import com.team2.dto.favorite.FavoriteDetailsDTO;
import com.team2.model.entity.Favorite;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMapper {

    private final ModelMapper modelMapper;

    public FavoriteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        // Configurar ModelMapper para usar estrategia estricta
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    // Mapeo de Collection a CollectionDetailsDTO (para mostrar información completa)
    public FavoriteDetailsDTO toDetailsDto(Favorite favorite) {
        FavoriteDetailsDTO favoriteDetailsDTO = modelMapper.map(favorite, FavoriteDetailsDTO.class);

        // Mapear manualmente el nombre completo del cliente
        favoriteDetailsDTO.setReaderName(
                favorite.getReader().getFirstName() + " " + favorite.getReader().getLastName()
        );

        return favoriteDetailsDTO;
    }

    // Mapeo de FavoriteCreateUpdateDTO a Favorite (para crear/actualizar)
    public Favorite toEntity(FavoriteCreateUpdateDTO favoriteCreateUpdateDTO) {
        return modelMapper.map(favoriteCreateUpdateDTO, Favorite.class);
    }

    // Mapeo de Collection a FavoriteCreateUpdateDTO (para casos donde necesites regresar el DTO de creación/actualización)
    public FavoriteCreateUpdateDTO toCreateUpdateDto(Favorite favorite) {
        return modelMapper.map(favorite, FavoriteCreateUpdateDTO.class);
    }
}