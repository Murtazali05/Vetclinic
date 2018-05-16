package su.vistar.vetclinic.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import org.springframework.validation.Validator;
import su.vistar.vetclinic.persistense.entity.UserEntity;
import su.vistar.vetclinic.service.DTO.UserDTO;
import su.vistar.vetclinic.service.UserService;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user = (UserDTO) o;

        if (user.getFio().length() < 5 || user.getFio().length() > 50) {
            errors.rejectValue("fio", "Full name must be between 5 and 50 characters!");
        }

        Date utilCurrentDate = new Date();
        java.sql.Date sqlCurrentDate = new java.sql.Date(utilCurrentDate.getTime());

        if (user.getBirthday().compareTo(sqlCurrentDate) == 1){
            errors.rejectValue("birthday", "Ошибка! Вы неправильно выбрали дату рождения!");
        }

        if (userService.getUserByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "Such email already exists!");
        }

        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(user.getEmail());

        if (!matcher.matches()) {
            errors.rejectValue("email", "Email entered incorrectly!");
        }

        if (user.getPassword().length() < 3 || user.getPassword().length() > 15) {
            errors.rejectValue("password", "Password must be over 3 characters!");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Password don't match!");
        }
    }
}
