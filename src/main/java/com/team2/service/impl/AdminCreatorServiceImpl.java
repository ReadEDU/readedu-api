package com.team2.service.impl;

import com.team2.dto.creator.CreatorDTO;
import com.team2.exception.BadRequestException;
import com.team2.exception.ResourceNotFoundException;
import com.team2.mapper.CreatorMapper;
import com.team2.model.entity.Creator;
import com.team2.repository.CreatorRepository;
import com.team2.service.AdminCreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminCreatorServiceImpl implements AdminCreatorService {
    private final CreatorRepository creatorRepository;
    private final CreatorMapper creatorMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CreatorDTO> getAll() {
        List<Creator> creators = creatorRepository.findAll();
        return creators.stream()
                .map(creatorMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CreatorDTO> paginate(Pageable pageable) {
        Page<Creator> creators = creatorRepository.findAll(pageable);
        return creators.map(creatorMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public CreatorDTO findById(Integer id) {
        Creator creator = creatorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El creador de contenido con ID "+id+" no fue encontrado"));
        return creatorMapper.toDTO(creator);
    }

    @Transactional
    @Override
    public CreatorDTO create(CreatorDTO creatorDTO) {
        creatorRepository.findByFirstNameAndLastName(creatorDTO.getFirstName(), creatorDTO.getLastName())
                        .ifPresent(existingCreator -> {
                            throw new BadRequestException("El creador de contenido ya existe con el mismo nombre y apellido");
                        });

        Creator creator = creatorMapper.toEntity(creatorDTO);
        creator.setCreatedAt(LocalDateTime.now());
        creator = creatorRepository.save(creator);
        return creatorMapper.toDTO(creator);
    }

    @Transactional
    @Override
    public CreatorDTO update(Integer id, CreatorDTO updateCreatorDTO) {
        Creator creatorFromDb = creatorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El creador con ID " + id + " no fue encontrado"));

        creatorRepository.findByFirstNameAndLastName(updateCreatorDTO.getFirstName(), updateCreatorDTO.getLastName())
                .filter(existingCreator -> !existingCreator.getId().equals(id))
                .ifPresent(existingCreator -> {
                    throw new BadRequestException("Ya existe un creador con el mismo nombre y apellido");
                });

        //Actualizar los campos
        creatorFromDb.setFirstName(updateCreatorDTO.getFirstName());
        creatorFromDb.setLastName(updateCreatorDTO.getLastName());
        creatorFromDb.setBiography(updateCreatorDTO.getBiography());
        creatorFromDb.setUpdatedAt(LocalDateTime.now());

        creatorFromDb = creatorRepository.save(creatorFromDb);
        return creatorMapper.toDTO(creatorFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Creator creator = creatorRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("El creador con ID " + id + " no fue encontrado"));
        creatorRepository.delete(creator);
    }
}