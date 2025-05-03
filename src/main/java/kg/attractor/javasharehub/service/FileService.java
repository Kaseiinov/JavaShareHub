package kg.attractor.javasharehub.service;

import kg.attractor.javasharehub.dto.CategoryDto;
import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FileService {
    void upload(FileDto fileDto);

    Page<FileDto> findAllFiles(Pageable pageable);

    List<CategoryDto> findAllCategory();
}
