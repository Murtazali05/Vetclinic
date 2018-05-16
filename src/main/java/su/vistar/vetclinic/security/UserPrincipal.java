package su.vistar.vetclinic.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import su.vistar.vetclinic.service.DTO.UserDTO;

import java.util.Collection;

public class UserPrincipal extends User {

    private UserDTO userDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public UserPrincipal(UserDTO user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.userDTO = user;
    }

    public UserPrincipal(UserDTO user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userDTO = user;
    }

}
