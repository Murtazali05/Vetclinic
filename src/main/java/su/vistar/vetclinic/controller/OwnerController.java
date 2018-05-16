package su.vistar.vetclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import su.vistar.vetclinic.security.UserPrincipal;
import su.vistar.vetclinic.service.AnimalService;
import su.vistar.vetclinic.service.DTO.AnimalDTO;
import su.vistar.vetclinic.service.DTO.UserDTO;
import su.vistar.vetclinic.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    private UserService userService;
    private AnimalService animalService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAnimalService(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public String ownerPage(@AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){

        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());
        if (userPrincipal.getUserDTO().getIdRole() == 2)
            return "doctor";
        return "owner";
    }

    @GetMapping("/animal/edit")
    public String editAnimal(@RequestParam(value = "id", required = false) Integer id,
                            @AuthenticationPrincipal UserPrincipal userPrincipal,
                            ModelMap map){
        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        if (id == null)
            map.addAttribute("animal", new AnimalDTO());
        else
            map.addAttribute("animal", animalService.getAnimalById(id));

        List<UserDTO> userDTOList = userService.getAllDoctor();
        map.addAttribute("doctors", userDTOList);

        return "add-animal";
    }

    @PostMapping("/animal/save")
    public String saveAnimal(@ModelAttribute("animal") @Valid AnimalDTO animalDTO, BindingResult bindingResult){
        UserPrincipal userPrincipal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        animalDTO.setIdOwner(userService.getUserByEmail(userPrincipal.getUsername()).getId());

        animalService.saveAnimal(animalDTO);

        return "redirect:/owner/animals";
    }

    @GetMapping("/animal/delete/{id}")
    public String deleteAnimal(@PathVariable Integer id){
        animalService.deleteAnimal(id);

        return "redirect:/owner/animals";
    }

    @GetMapping("animals")
    public String animalList(@AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){
        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        map.addAttribute("animals", animalService.getAllAnimals());
        return "animals";
    }

}
