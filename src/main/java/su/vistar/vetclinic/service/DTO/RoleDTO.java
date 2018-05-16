package su.vistar.vetclinic.service.DTO;

import lombok.Getter;
import lombok.Setter;
import su.vistar.vetclinic.persistense.entity.RoleEntity;

@Getter
@Setter
public class RoleDTO {
    private int id;
    private String role;

    public RoleDTO(RoleEntity roleEntity) {
        this.id = roleEntity.getIdRole();
        this.role = roleEntity.getRole();
    }
}
