package kg.attractor.javasharehub.service;

import kg.attractor.javasharehub.dto.FileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FileService {
    Page<FileDto> findAllFiles(Pageable pageable);
}
