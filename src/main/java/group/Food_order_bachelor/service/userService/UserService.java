package group.Food_order_bachelor.service.userService;

import group.Food_order_bachelor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findUserByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//                return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
