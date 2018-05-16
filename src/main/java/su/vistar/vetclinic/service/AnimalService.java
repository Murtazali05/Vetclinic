package su.vistar.vetclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.vistar.vetclinic.persistense.entity.AnimalEntity;
import su.vistar.vetclinic.persistense.entity.UserEntity;
import su.vistar.vetclinic.persistense.repository.AnimalRepository;
import su.vistar.vetclinic.persistense.repository.UserRepository;
import su.vistar.vetclinic.service.DTO.AnimalDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {
    private AnimalRepository animalRepository;
    private UserRepository userRepository;

    @Autowired
    public void setAnimalRepository(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public AnimalDTO getAnimalById(Integer id){
        return new AnimalDTO(animalRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<AnimalDTO> getAllAnimals(){
        List<AnimalEntity> animalEntityList = animalRepository.findAll();
        List<AnimalDTO> animalDTOList = new ArrayList<>();

        for (int i = 0; i < animalEntityList.size(); i++){
            animalDTOList.add(new AnimalDTO(animalEntityList.get(i)));
        }

        return animalDTOList;
    }

    @Transactional
    public void saveAnimal(AnimalDTO animalDTO){
        AnimalEntity animalEntity = new AnimalEntity();

        animalEntity.setIdAnimal(animalDTO.getId());
        animalEntity.setName(animalDTO.getName());
        animalEntity.setBirthday(animalDTO.getBirthday());
        animalEntity.setBreed(animalDTO.getBreed());
        animalEntity.setGender(animalDTO.getGender());
        animalEntity.setPhoto(animalDTO.getPhoto());
        animalEntity.setOwner(userRepository.findOne(animalDTO.getIdOwner()));
        animalEntity.setDoctor(userRepository.findOne(animalDTO.getIdDoctor()));
        animalRepository.save(animalEntity);
    }

    @Transactional
    public void deleteAnimal(Integer id){
        animalRepository.delete(id);
    }

}
