package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    public Student createStudent(Student student) {
       return studentRepository.save(student);
    }
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    public Collection<Student> getAllStudentByAge(Integer age){
      return studentRepository.getAllStudentByAge(age);
    }
     public Collection<Student> getAllStudent(){
        return studentRepository.findAll();
     }
}

