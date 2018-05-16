package su.vistar.vetclinic.service.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import su.vistar.vetclinic.persistense.entity.UserEntity;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String fio;
    private Date birthday;
    private String email;
    private String password;
    private String confirmPassword;
    private String photo;
    private int idRole;

    public UserDTO(UserEntity userEntity) {
        this.id = userEntity.getIdUser();
        this.fio = userEntity.getFio();
        this.birthday = userEntity.getBirthday();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.confirmPassword = userEntity.getPassword();
        this.photo = userEntity.getPhoto();
        this.idRole = userEntity.getRole().getIdRole();
    }

}
