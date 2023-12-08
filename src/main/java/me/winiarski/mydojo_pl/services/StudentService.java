package me.winiarski.mydojo_pl.services;

import me.winiarski.mydojo_pl.controllers.StudentController;
import me.winiarski.mydojo_pl.entities.Student;
import me.winiarski.mydojo_pl.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentsList(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.getReferenceById(id);
    }

    public void postNewStudent(Student student){
        studentRepository.save(student);
    }

}
