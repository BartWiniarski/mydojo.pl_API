package me.winiarski.mydojo_pl.services;

import me.winiarski.mydojo_pl.controllers.StudentController;
import me.winiarski.mydojo_pl.entities.Student;
import me.winiarski.mydojo_pl.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentsList() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.getReferenceById(id);
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentByEmail =
                studentRepository.findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("User with provided e-mail already exists.");
        }
        studentRepository.save(student);
    }

    public void deleteStudentById(Long id) {
        boolean exists = studentRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Student with provided ID: " + id + " does not exists.");
        }
        studentRepository.deleteById(id);
    }

    public void updateStudent(Student student) {
        Long id = student.getId();
        boolean exists = studentRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Student with provided ID: " + id + " does not exists.");
        }
        studentRepository.save(student);
    }
}
