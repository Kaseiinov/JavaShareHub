package kg.attractor.javasharehub.repository;

import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findByFileName(String fileName);

    @Query(nativeQuery = true,
            value = "SELECT f.* FROM file f " +
                    "JOIN usr_file uf ON uf.file_id = f.id " +
                    "JOIN users u ON u.id = uf.usr_id " +
                    "WHERE LOWER(u.email) = LOWER(:email)")

    List<File> findAllByUserEmail(@Param("email") String email);
}
