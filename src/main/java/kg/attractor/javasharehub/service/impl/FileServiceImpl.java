package kg.attractor.javasharehub.service.impl;

import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.dto.UserDto;
import kg.attractor.javasharehub.model.File;
import kg.attractor.javasharehub.repository.FileRepository;
import kg.attractor.javasharehub.repository.UserRepository;
import kg.attractor.javasharehub.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    @Override
    public Page<FileDto> findAllFiles(Pageable pageable) {
        Page<File> files = fileRepository.findAll(pageable);
        return filePageBuilder(files, pageable);
    }

    public Page<FileDto> filePageBuilder(Page<File> files, Pageable pageable) {
        List<FileDto> filesDto = files.getContent()
                .stream()
                .map(e -> FileDto.builder()
                        .id(e.getId())
                        .fileName(e.getFileName())
                        .category(e.getCategory())
                        .Users(e.getUsers().stream().map(u -> UserDto.builder()
                                .id(u.getId())
                                .email(u.getEmail())
                                .password(u.getPassword())
                                .enabled(u.getEnabled())
                                .build())
                                .toList())
                        .build())
                .toList();
        return new PageImpl<>(filesDto, pageable, files.getTotalElements());
    }
}
