package su.vistar.vetclinic.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import su.vistar.vetclinic.persistense.entity.RoleEntity;
import su.vistar.vetclinic.persistense.entity.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);

    List<UserEntity> findAllByRole(RoleEntity role);

}
