package pe.edu.upc.gamarra.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pe.edu.upc.gamarra.entities.Role;
import pe.edu.upc.gamarra.entities.RoleName;
import pe.edu.upc.gamarra.entities.User;
import pe.edu.upc.gamarra.message.request.LoginForm;
import pe.edu.upc.gamarra.message.request.SignUpForm;
import pe.edu.upc.gamarra.message.response.JwtResponse;
import pe.edu.upc.gamarra.message.response.ResponseMessage;
import pe.edu.upc.gamarra.repository.RoleRepository;
import pe.edu.upc.gamarra.repository.UserRepository;
import pe.edu.upc.gamarra.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Api(value = "API para la autenticación", tags = {"Auth"})
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	@ApiOperation("Autenticación de usuario")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Optional<User> userSignIn = userRepository.findByUsername(userDetails.getUsername());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userSignIn.get().getId(), userDetails.getAuthorities()));
	}

	@PostMapping("/signup")
	@ApiOperation("Registro de usuario")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fallo -> ¡El nombre de usuario ya está en uso!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fallo -> ¡El correo electrónico ya está en uso!"),
					HttpStatus.BAD_REQUEST);
		}

		
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("¡Fallo! -> Causa: El rol del usuario no se encuentra."));
				roles.add(adminRole);

				break;
			case "pm":
				Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
						.orElseThrow(() -> new RuntimeException("¡Fallo! -> Causa: El rol del usuario no se encuentra."));
				roles.add(pmRole);

				break;
			default:
				Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("¡Fallo! -> Causa: El rol del usuario no se encuentra."));
				roles.add(userRole);
			}
		});

		user.setRoles(roles);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("Usuario registrado correctamente!"), HttpStatus.OK);
	}
}