package com.isy2202.grupo1.service;

import com.isy2202.grupo1.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario saveUsuario(Usuario usuario);
    public Optional<Usuario> findByCorreo(String correo);
    List<Usuario> getAllUsuarios();
    Usuario updateUsuario(Long id, Usuario usuarioDetails);
    void deleteUsuario(Long id);
}