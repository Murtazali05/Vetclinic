package su.vistar.vetclinic.service.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import su.vistar.vetclinic.persistense.entity.AnimalEntity;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class AnimalDTO {
    private int id;
    private String name;
    private Date birthday;
    private String breed;
    private String gender;
    private String photo;
    private int idOwner;
    private int idDoctor;

    public AnimalDTO(AnimalEntity animalEntity) {
        id = animalEntity.getIdAnimal();
        name = animalEntity.getName();
        birthday = animalEntity.getBirthday();
        breed = animalEntity.getBreed();
        gender = animalEntity.getGender();
        photo = animalEntity.getPhoto();
        idOwner = animalEntity.getOwner().getIdUser();
        idDoctor = animalEntity.getDoctor().getIdUser();
    }
}
