package su.vistar.vetclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import su.vistar.vetclinic.security.UserPrincipal;
import su.vistar.vetclinic.service.UserService;

@Controller
public class IndexController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){

        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        return "index";
    }

}
