package aueb.gr.pm.schoolapp.authentication;

import aueb.gr.pm.schoolapp.core.exceptions.AppObjectNotAuthorizedException;
import aueb.gr.pm.schoolapp.dto.AuthenticationRequestDTO;
import aueb.gr.pm.schoolapp.dto.AuthenticationResponseDTO;
import aueb.gr.pm.schoolapp.model.User;
import aueb.gr.pm.schoolapp.repository.UserRepository;
import aueb.gr.pm.schoolapp.security.JwTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwTService jwTService;
    private final UserRepository userRepository;

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO dto) throws AppObjectNotAuthorizedException {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),
                dto.getPassword()));

        User user = userRepository.findUserByUsername(authentication.getName())
                .orElseThrow(() -> new AppObjectNotAuthorizedException("User" , "User not authorized"));

        String token = jwTService.generateToken(authentication.getName() , user.getRole().name());
        return new AuthenticationResponseDTO(user.getFirstname() , user.getLastname() , token);
    }


}
