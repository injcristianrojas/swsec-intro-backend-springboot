package cl.injcristianrojas.security.jwt;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cl.injcristianrojas.data.model.UserJPA;

import java.util.Collection;

public class MainUserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	private UserJPA user;

    public MainUserPrincipal(UserJPA user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    public String getMainRole() {
        GrantedAuthority first = getAuthorities().iterator().next();
        return first.toString();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
