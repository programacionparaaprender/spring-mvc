package com.programacion.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.programacion.app.model.Usuario;

import net.sf.json.JSONSerializer;
import java.util.List;
import java.util.ArrayList;


@Controller
@RequestMapping("/seguridadComercial")
public class AlertControler {

    @RequestMapping(value = "listaTodosUsuarios.htm")
    public @ResponseBody
    String listaTodosUsuarios() {
    	Usuario usuario = new Usuario();
        usuario.setNombre("Nombre");
    	return JSONSerializer.toJSON(usuario).toString();
    }

}
