package kg.attractor.javasharehub.repository;

import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
