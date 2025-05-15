package kg.attractor.javasharehub.service.impl;

import kg.attractor.javasharehub.dto.CategoryDto;
import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.dto.UserDto;
import kg.attractor.javasharehub.exceptions.FileNotFoundException;
import kg.attractor.javasharehub.model.Category;
import kg.attractor.javasharehub.model.File;
import kg.attractor.javasharehub.model.User;
import kg.attractor.javasharehub.repository.CategoryRepository;
import kg.attractor.javasharehub.repository.FileRepository;
import kg.attractor.javasharehub.repository.UserRepository;
import kg.attractor.javasharehub.service.FileService;
import kg.attractor.javasharehub.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Override
    public List<FileDto> findAllFilesByUser(String email){
        List<File> files = fileRepository.findAllByUserEmail(email);
        return fileListBuilder(files);
    }

    @Override
    public FileDto findByName(String fileName){
        File file = fileRepository.findByFileName(fileName).orElseThrow(FileNotFoundException::new);
        return fileBuilder(file);
    }

    @Override
    public ResponseEntity<?> download(String fileName){
        return fileUtil.getOutputFile(fileName, "files/", MediaType.ALL);
    }

    @Override
    public void upload(FileDto fileDto){
        String filename = fileUtil.saveUploadFile(fileDto.getFile(), "files/");

//        User user = userRepository.findById(fileDto.getUsers().getLast().getId()).orElseThrow(UserNotFoundException::new);

        File file = new File();
        file.setUsers(fileDto.getUsers().stream().map(e -> User.builder()
                .id(e.getId())
                .email(e.getEmail())
                .password(e.getPassword())
                .enabled(e.getEnabled())
                .build()).toList());

        file.setFileName(filename);
        file.setCategory(categoryRepository.findById(fileDto.getCategoryId()).orElseThrow());
        file.setStatus(fileDto.getStatus().toUpperCase());

        fileRepository.save(file);
    }

    @Override
    public Page<FileDto> findAllFiles(Pageable pageable) {
        Page<File> files = fileRepository.findAll(pageable);
        return filePageBuilder(files, pageable);
    }

    @Override
    public List<CategoryDto> findAllCategory(){
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(
                e -> CategoryDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .build())
                .toList();

    }

    public Page<FileDto> filePageBuilder(Page<File> files, Pageable pageable) {
        List<FileDto> filesDto = files.getContent()
                .stream()
                .map(e -> FileDto.builder()
                        .id(e.getId())
                        .fileName(e.getFileName())
                        .categoryId(e.getCategory().getId())
                        .Users(e.getUsers().stream().map(u -> UserDto.builder()
                                .id(u.getId())
                                .email(u.getEmail())
                                .password(u.getPassword())
                                .enabled(u.getEnabled())
                                .build())
                                .toList())
                        .status(e.getStatus().toUpperCase())
                        .build())
                .toList();
        return new PageImpl<>(filesDto, pageable, files.getTotalElements());
    }

    public List<FileDto> fileListBuilder(List<File> files) {
        return files
                .stream()
                .map(e -> FileDto.builder()
                        .id(e.getId())
                        .fileName(e.getFileName())
                        .categoryId(e.getCategory().getId())
                        .Users(e.getUsers().stream().map(u -> UserDto.builder()
                                        .id(u.getId())
                                        .email(u.getEmail())
                                        .password(u.getPassword())
                                        .enabled(u.getEnabled())
                                        .build())
                                .toList())
                        .status(e.getStatus().toUpperCase())
                        .build())
                .toList();

    }

    public FileDto fileBuilder(File file){
        return FileDto.builder()
                .id(file.getId())
                .fileName(file.getFileName())
                .categoryId(file.getCategory().getId())
                .status(file.getStatus().toUpperCase())
                .build();
    }
}
