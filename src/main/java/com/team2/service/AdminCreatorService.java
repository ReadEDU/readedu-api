package com.team2.service;

import com.team2.dto.creator.CreatorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface AdminCreatorService {
    List<CreatorDTO> getAll();
    Page<CreatorDTO> paginate(Pageable pageable);
    CreatorDTO findById(Integer id);
    CreatorDTO create(CreatorDTO CreatorDTO);
    CreatorDTO update(Integer id, CreatorDTO updateCreatorDTO);
    void delete(Integer id);
}
