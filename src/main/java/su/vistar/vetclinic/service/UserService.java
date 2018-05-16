package su.vistar.vetclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.vistar.vetclinic.persistense.entity.RoleEntity;
import su.vistar.vetclinic.persistense.entity.UserEntity;
import su.vistar.vetclinic.persistense.repository.RoleRepository;
import su.vistar.vetclinic.persistense.repository.UserRepository;
import su.vistar.vetclinic.service.DTO.RoleDTO;
import su.vistar.vetclinic.service.DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional(readOnly = true)
    public UserDTO getUserByEmail(String email){
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity != null)
            return new UserDTO(userEntity);
        else
            return null;
    }

    public List<UserDTO> getAllDoctor(){
        RoleEntity role = roleRepository.findOne(2);
        List<UserEntity> userEntities = userRepository.findAllByRole(role);
        List<UserDTO> userDTOS = new ArrayList<>();

        for (int i = 0; i < userEntities.size(); i++){
            userDTOS.add(new UserDTO(userEntities.get(i)));
        }

        return userDTOS;
    }

    @Transactional(readOnly = true)
    public RoleEntity getRole(Integer id){
        return roleRepository.findOne(id);
    }

    @Transactional
    public void createUser(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setFio(userDTO.getFio());
        userEntity.setBirthday(userDTO.getBirthday());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userEntity.setPhoto(userDTO.getPhoto());
        userEntity.setRole(roleRepository.findOne(userDTO.getIdRole()));
        userRepository.save(userEntity);
    }
}
