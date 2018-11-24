package ru.yegorpilipenko.otus.spring.m2h4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m2h4.model.User;
import ru.yegorpilipenko.otus.spring.m2h4.repository.UserRepository;

import static java.lang.String.format;
import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public final class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("User '%s' is not exists", username)));
        return toUserDetails(user);
    }

    private UserDetails toUserDetails(final User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                emptyList()
        );
    }

}
