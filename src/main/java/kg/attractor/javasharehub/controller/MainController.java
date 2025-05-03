package kg.attractor.javasharehub.controller;

import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final FileService fileService;


    @GetMapping
    public String index(@RequestParam(defaultValue = "0")int page, Model model) {
        int pageSize = 5;

        Page<FileDto> files = fileService.findAllFiles(PageRequest.of(page, pageSize));

        model.addAttribute("files", files.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("hasNext", files.hasNext());
        model.addAttribute("hasPrevious", files.hasPrevious());
        model.addAttribute("totalPages", files.getTotalPages());
        return "index";
    }

}