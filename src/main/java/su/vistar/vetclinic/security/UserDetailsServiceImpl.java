package su.vistar.vetclinic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.vistar.vetclinic.persistense.entity.RoleEntity;
import su.vistar.vetclinic.service.DTO.UserDTO;
import su.vistar.vetclinic.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO user = userService.getUserByEmail(email);

        return new UserPrincipal(
                user,
                buildUserAuthorities(userService.getRole(user.getIdRole()))
        );
    }

    private Set<GrantedAuthority> buildUserAuthorities(RoleEntity role) {
        Set<GrantedAuthority> auth = new HashSet<GrantedAuthority>();

//        TODO: add multiple roles!

        auth.add(new SimpleGrantedAuthority(String.format("ROLE_%s", role.getRole())));
        return auth;
    }
}
