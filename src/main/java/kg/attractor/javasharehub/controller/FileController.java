package kg.attractor.javasharehub.controller;

import jakarta.validation.Valid;
import kg.attractor.javasharehub.dto.CategoryDto;
import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.dto.UserDto;
import kg.attractor.javasharehub.model.Category;
import kg.attractor.javasharehub.model.User;
import kg.attractor.javasharehub.service.FileService;
import kg.attractor.javasharehub.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    private final UserService userService;

    @GetMapping("upload")
    public String upload(Model model) {
        List<CategoryDto> categories = fileService.findAllCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("file", new FileDto());
        return "file/upload";
    }

    @PostMapping("upload")
    public String upload(@Valid FileDto fileDto, BindingResult bindingResult,  Model model, Authentication auth){
        if(!bindingResult.hasErrors()){
            UserDto user = userService.getUserByEmail(auth.getName());
            fileDto.setUsers(List.of(user));
            fileService.upload(fileDto);
            log.info("upload file {}", fileDto.getFileName());
            return "redirect:/users/profile";
        }
        model.addAttribute("file", fileDto);
        model.addAttribute("categories", fileService.findAllCategory());
        return "file/upload";
    }

    @GetMapping("download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) throws IOException {
        return fileService.download(fileName);
    }





}
