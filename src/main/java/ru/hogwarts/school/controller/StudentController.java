package ru.hogwarts.school.controller;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("students")

public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("id")
    public ResponseEntity<Student> getStudentById(@RequestParam("id") Long id){
       Student student = studentService.getStudentById(id);
       if (student==null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(student);
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }
    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
       Student updateStudent = studentService.updateStudent(student);
       if (updateStudent==null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(updateStudent);
    }
    @DeleteMapping("{id}")
    public Student deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }
    @GetMapping("age")
    public ResponseEntity<Collection<Student>> getAllStudentByAge(@RequestParam("age") Integer age){
        Collection<Student> students = studentService.getAllStudentByAge(age);
        if (students==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }
    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudent(){
        Collection<Student> allStudents = studentService.getAllStudent();
        if (allStudents==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allStudents);
    }
}
