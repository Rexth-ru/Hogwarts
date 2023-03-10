package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
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
            @RequestParam(required = false) Long facultyId){
        if (age!=null) {
            return ResponseEntity.ok(studentService.getAllStudentByAge(age));
        }
        if (maxAge!=null && minAge!=null){
            return ResponseEntity.ok(studentService.getAllStudentByAgeBetween(minAge, maxAge));
        }
        if (facultyId!=null){
            return ResponseEntity.ok(studentService.getAllStudentByFacultyId(facultyId));
        }
        return ResponseEntity.ok(studentService.getAllStudent());
    }
//    @GetMapping({"faculty"})
//    public ResponseEntity getFacultyByIdStudent(@PathVariable Long id){
//        FacultyDTO facultyDTO = studentService.getFacultyByIdStudent(id);
//        if (facultyDTO==null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(facultyDTO);
//    }
}
