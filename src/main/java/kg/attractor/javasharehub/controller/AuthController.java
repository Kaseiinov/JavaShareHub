package kg.attractor.javasharehub.controller;


import jakarta.validation.Valid;
import kg.attractor.javasharehub.dto.UserDto;
import kg.attractor.javasharehub.exceptions.SuchEmailAlreadyExistsException;
import kg.attractor.javasharehub.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@Slf4j
@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("register")
    public String register(Model model){
        model.addAttribute("userDto", new UserDto());
        return "auth/register";
    }

    @PostMapping("register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult, Model model) throws RoleNotFoundException, SuchEmailAlreadyExistsException {
        if(!bindingResult.hasErrors()){
            log.info("register {}", userDto.getEmail());
            userService.addUser(userDto);
            return "redirect:/auth/login";
        }
        model.addAttribute("userDto", userDto);
        return "auth/register";
    }

    @GetMapping("login")
    public String login(Model model){
        return "auth/login";
    }



}
