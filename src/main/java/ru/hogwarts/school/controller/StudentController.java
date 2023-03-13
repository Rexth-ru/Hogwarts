package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Avatar;
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
//    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> uploadAvatar(@PathVariable Long id,
//                                               @RequestParam MultipartFile avatar) throws IOException {
//        if (avatar.getSize() >= 1024*300){
//            return ResponseEntity.badRequest().body("File is very big!");
//        }
//        avatarService.uploadAvatar(id, avatar);
//        return ResponseEntity.ok().build();
//    }
//    @GetMapping(value = "/{id}/avatar/preview")
//    public ResponseEntity<byte[]> dounloadAvatar(@PathVariable Long id){
//        Avatar avatar = avatarService.findAvatar(id);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
//        httpHeaders.setContentLength(avatar.getData().length);
//        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(avatar.getData());
//    }
//    @GetMapping(value = "/{id}/avatar")
//    public void dounloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException{
//        Avatar avatar = avatarService.findAvatar(id);
//        Path path = Path.of(avatar.getFilePath());
//        try (InputStream inputStream = Files.newInputStream(path);
//             OutputStream outputStream = response.getOutputStream();) {
//            response.setStatus(200);
//            response.setContentType(avatar.getMediaType());
//            response.setContentLength((int)avatar.getFileSize());
//            inputStream.transferTo(outputStream);
//        }
//    }
}
