package kg.attractor.javasharehub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kg.attractor.javasharehub.model.Category;
import kg.attractor.javasharehub.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
    private Long id;
    private List<UserDto> Users;
    @NotNull
    private Long categoryId;
    @NotNull
    private MultipartFile file;
    private String fileName;
    @NotBlank
    private String status;

}
