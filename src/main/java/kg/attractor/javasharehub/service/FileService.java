package kg.attractor.javasharehub.service;

import kg.attractor.javasharehub.dto.CategoryDto;
import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface FileService {
    List<FileDto> findAllFilesByUser(String email);

    FileDto findByName(String fileName);


    ResponseEntity<?> download(String fileName);

    void upload(FileDto fileDto);

    Page<FileDto> findAllFiles(Pageable pageable);

    List<CategoryDto> findAllCategory();
}
