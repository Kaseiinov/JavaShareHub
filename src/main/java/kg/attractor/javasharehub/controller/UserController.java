package kg.attractor.javasharehub.controller;

import jakarta.validation.Valid;
import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.dto.UserDto;
import kg.attractor.javasharehub.model.User;
import kg.attractor.javasharehub.service.FileService;
import kg.attractor.javasharehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FileService fileService;

    @GetMapping("profile")
    public String profile(Model model, Authentication auth) {
        UserDto user  = userService.getUserByEmail(auth.getName());
        List<FileDto> files = fileService.findAllFilesByUser(user.getEmail());
        model.addAttribute("files", files);
        model.addAttribute("user", user);
        return "auth/profile";
    }

}
