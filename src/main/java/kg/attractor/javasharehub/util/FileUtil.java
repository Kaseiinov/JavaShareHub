package kg.attractor.javasharehub.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kg.attractor.javasharehub.model.File;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUtil {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final static String UPLOAD_DIR = "data/";


    @SneakyThrows
    public String saveUploadFile(MultipartFile file, String subDir) {
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "_" + file.getOriginalFilename();

        Path pathDir = Paths.get(UPLOAD_DIR + subDir);
        Files.createDirectories(pathDir);

        Path filePath = Paths.get(pathDir + "/" + resultFileName);
        if(!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        try(OutputStream os = Files.newOutputStream(filePath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultFileName;
    }

    @SneakyThrows
    public ResponseEntity<?> getOutputFile(String fileName, String subDir, MediaType mediaType) {
        String clearFileName = fileName.split("_", 2)[1]; // безопаснее указать limit
        String encodedFileName = URLEncoder.encode(clearFileName, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");
        try {
            byte[] image = Files.readAllBytes(Paths.get(UPLOAD_DIR + subDir + fileName));
            Resource resource = new ByteArrayResource(image);

            String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    .contentLength(resource.contentLength())
                    .contentType(MediaTypeFactory.getMediaType(fileName)
                            .orElse(MediaType.APPLICATION_OCTET_STREAM))
                    .body(resource);
        } catch (NoSuchFileException nsf) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No such file");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading file");
        }
    }





}
