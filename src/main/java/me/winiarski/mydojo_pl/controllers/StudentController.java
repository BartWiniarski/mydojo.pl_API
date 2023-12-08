package me.winiarski.mydojo_pl.controllers;

import me.winiarski.mydojo_pl.entities.Student;
import me.winiarski.mydojo_pl.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public List<Student> getStudentsList() {
        return studentService.getStudentsList();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/")
    public void posStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }

    @PutMapping("/")
    public void putStudent(@RequestBody Student student){
        studentService.updateStudent(student);
    }
}
