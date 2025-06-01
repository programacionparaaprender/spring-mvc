package com.programacion.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luv2code.jsf.jdbc.Student;
import com.luv2code.jsf.jdbc.StudentDbUtil;
import com.programacion.app.model.Usuario;

import net.sf.json.JSONSerializer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Controller
@RequestMapping("/security")
public class StudentControler {
List<Usuario> users;
	
	private List<Student> students;
	private StudentDbUtil studentDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public StudentControler() throws Exception {
		students = new ArrayList<>();
		
		studentDbUtil = StudentDbUtil.getInstance();
	}
	
	@PostConstruct
	public void init() {
		logger.info("Loading students");
		students.clear();
		try {
			// get all students from database
			students = studentDbUtil.getStudents();
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading students", exc);
			
		}
	}
	
	public void initAntiguo() {
	    Connection conexion = null; 
	    users = new java.util.LinkedList<Usuario>();
	    try {
	        Class.forName("org.postgresql.Driver");
	        String connectionUrl = "jdbc:postgresql://localhost:5434/spring_mvc?user=sa&password=123";
	        conexion = DriverManager.getConnection(connectionUrl);
	    } catch (SQLException e) {
	        System.out.println("SQL Exception: "+ e.toString());
	    } catch (ClassNotFoundException cE) {
	        System.out.println("Class Not Found Exception: "+ cE.toString());
	    }
	    
	    String sql = "SELECT id, nombre, email, password FROM users LIMIT 1000;";
	    
	    try (PreparedStatement cmd = conexion.prepareStatement(sql)) {
	        ResultSet rs = cmd.executeQuery();
	        
	        while(rs.next()) {
	            String name = rs.getString(2);
	            Long id = rs.getLong(1);
	            String email = rs.getString(3);
	            String password = rs.getString(4);
	            Usuario temp = new Usuario(id, name, email, password);
	            users.add(temp);
	        }
	        conexion.close();
	    }
	    catch(Exception ex) {
	        ex.printStackTrace(); // Mejor manejo de errores
	    }
	}
	
	
	@RequestMapping("/list-student")
    public String listStudent(Model model) {
    	model.addAttribute("students", students);
    	return "list-students";
    }
	
    @RequestMapping(value = "listUsers.htm")
    public @ResponseBody
    String listaTodosUsuarios() {
    	Student student = students.get(0);
        return JSONSerializer.toJSON(student).toString();
    }

}
