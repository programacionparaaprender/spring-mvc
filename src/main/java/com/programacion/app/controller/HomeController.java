package com.programacion.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @RequestMapping("/")
    public String home(Model model) {
        // Agregar datos al modelo
        model.addAttribute("titulo", "Bienvenido a Spring MVC 3.0.5");
        model.addAttribute("mensaje", "Esto es un mensaje desde el controlador");
        model.addAttribute("fecha", new java.util.Date());
        
        // Lista de ejemplo
        java.util.List<String> tecnologias = new java.util.ArrayList<String>();
        tecnologias.add("Spring MVC 3.0.5");
        tecnologias.add("Java 1.7");
        tecnologias.add("JSP 2.1");
        model.addAttribute("tecnologias", tecnologias);
        
        return "index"; // Nombre de la vista JSP (sin extensión)
    }
}