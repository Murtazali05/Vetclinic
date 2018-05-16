package su.vistar.vetclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import su.vistar.vetclinic.security.AuthenticationService;
import su.vistar.vetclinic.service.DTO.RoleDTO;
import su.vistar.vetclinic.service.DTO.UserDTO;
import su.vistar.vetclinic.service.UserService;
import su.vistar.vetclinic.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AuthController {
    private UserService userService;

    private UserValidator userValidator;
    private AuthenticationService authenticationService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, ModelMap map){
        if (error != null)
            map.addAttribute("error", "Ошибка! Неверный логин или пароль!");

        return "login";
    }

    @GetMapping("/registration")
    public String registration(ModelMap map){

        map.addAttribute("user", new UserDTO());

        return "registration";
    }

    @PostMapping("/registration")
    public String toRegister(@ModelAttribute UserDTO userDTO, BindingResult bindingResult, ModelMap map){
        userDTO.setIdRole(userService.getRole(3).getIdRole());

        userValidator.validate(userDTO, bindingResult);

        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            map.addAttribute("user", userDTO);
            map.addAttribute("error", fieldErrors.get(0).getCode());

            return "registration";
        }

        userService.createUser(userDTO);

        authenticationService.authWithAuthManager(userDTO);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}
