package com.team2.api;

import com.team2.dto.creator.CreatorDTO;
import com.team2.service.AdminCreatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/creators")
@RequiredArgsConstructor
public class AdminCreatorController {

    private final AdminCreatorService adminCreatorService;

    @GetMapping
    public ResponseEntity<List<CreatorDTO>> listAll() {
        List<CreatorDTO> creators = adminCreatorService.getAll();
        return new ResponseEntity<>(creators, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CreatorDTO>> paginate(@PageableDefault(size = 5, sort = "firstName") Pageable pageable) {

        Page<CreatorDTO> page = adminCreatorService.paginate(pageable);
        return new ResponseEntity<>(page,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreatorDTO> create(@Valid @RequestBody CreatorDTO creatorDTO) {
        CreatorDTO createdCreator = adminCreatorService.create(creatorDTO);
        return new ResponseEntity<>(createdCreator,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatorDTO> getById(@PathVariable Integer id) {
        CreatorDTO creator = adminCreatorService.findById(id);
        return new ResponseEntity<>(creator,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreatorDTO> update(@PathVariable Integer id, @Valid @RequestBody CreatorDTO creatorDTO) {
        CreatorDTO updatedCreator = adminCreatorService.update(id, creatorDTO);
        return new ResponseEntity<>(updatedCreator,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        adminCreatorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
