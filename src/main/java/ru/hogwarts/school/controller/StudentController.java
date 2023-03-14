package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("students")

public class StudentController {
    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }
    @GetMapping("{id}")
    public ResponseEntity getStudentById(@PathVariable Long id){
       StudentDTO studentDTO = studentService.getStudentById(id);
       if (studentDTO==null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(studentDTO);
    }
    @PostMapping
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }
    @PutMapping
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO){
       StudentDTO updateStudentDTO = studentService.updateStudent(studentDTO);
       if (updateStudentDTO==null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(updateStudentDTO);
    }
    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
    @GetMapping
    public ResponseEntity<Collection<StudentDTO>> getStudents(
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Integer minAge, @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) Long facultyId,
            @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer count)
            throws IOException {
        if (age!=null) {
            return ResponseEntity.ok(studentService.getAllStudentByAge(age));
        }
        if (maxAge!=null && minAge!=null){
            return ResponseEntity.ok(studentService.getAllStudentByAgeBetween(minAge, maxAge));
        }
        if (facultyId!=null){
            return ResponseEntity.ok(studentService.getAllStudentByFacultyId(facultyId));
        }
        if (page!=null && count!=null) {
            return ResponseEntity.ok(studentService.getAllStudent(page, count));
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/count-student")
    public Long countAllStudent(){
        return studentService.countAllStudent();
    }
    @GetMapping("/avg-age")
    public Integer getAvgAge(){
        return studentService.avgByAge();
    }

    @GetMapping("/young")
    public ResponseEntity<Collection<StudentDTO>> getFirstStudent(){
       return ResponseEntity.ok(studentService.getFirstStudentByAge());
    }
}
