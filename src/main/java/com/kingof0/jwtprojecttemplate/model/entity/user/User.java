package com.kingof0.jwtprojecttemplate.model.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Builder.Default
    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private List<Authority> authorities = new ArrayList<>();

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private UserContact contact;

    @ElementCollection
    @Builder.Default
    private Set<String> firebaseTokens = new HashSet<>();

    private LocalDateTime lastLogin;

    private LocalDateTime lastActivity;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().map(Authority::getSimpleGrantedAuthority).toList();
    }

    public List<Authority> getAuthoritiesSet() {
        return authorities;
    }

    public boolean hasAuthority(Authority authority) {
        return authorities.contains(authority);
    }

    public boolean hasAnyAuthority(Authority... authorities) {
        for (Authority authority : authorities) {
            if (this.authorities.contains(authority)) return true;
        }
        return false;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
    }

    public void addFirebaseToken(String firebaseToken) {
        this.firebaseTokens.add(firebaseToken);
    }

    public void removeFirebaseToken(String firebaseToken) {
        this.firebaseTokens.remove(firebaseToken);
    }
}
