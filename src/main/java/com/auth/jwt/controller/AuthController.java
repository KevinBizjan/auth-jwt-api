package com.auth.jwt.controller;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.jwt.dto.AuthResponse;
import com.auth.jwt.security.JwtService;
import com.auth.jwt.model.Rol;
import com.auth.jwt.model.Usuario;
import com.auth.jwt.repository.RolRepository;
import com.auth.jwt.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UsuarioRepository usuarioRepository,
                        RolRepository rolRepository,
                        BCryptPasswordEncoder passwordEncoder,
                        JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // REGISTRO
    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) {

        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        Rol rolUser = rolRepository.findByNombre("USER")
                .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol(rolUser);
        usuario.setActivo(true);

        return usuarioRepository.save(usuario);
    }

    // LOGIN (SIN JWT)
    @PostMapping("/login")
    public AuthResponse login(@RequestBody Usuario usuario) {

        Optional<Usuario> userOpt = usuarioRepository.findByUsername(usuario.getUsername());

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Usuario user = userOpt.get();

        if (!passwordEncoder.matches(usuario.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Generamos token JWT
        String token = jwtService.generateToken(user.getUsername());
        
        return new AuthResponse(token);
    }
}
