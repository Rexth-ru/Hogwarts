package ru.hogwarts.school.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentDTO.toStudent();
       return StudentDTO.studentToDTO(studentRepository.save(student));
    }

    public StudentDTO getStudentById(Long id) {
        return StudentDTO.studentToDTO(studentRepository.findById(id).orElse(null));
    }

    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Student student = studentDTO.toStudent();
        return StudentDTO.studentToDTO(studentRepository.save(student));
    }
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    public Collection<StudentDTO> getAllStudentByAge(Integer age){
        Collection<Student> students = studentRepository.getAllStudentByAge(age);
       return students.stream().map(StudentDTO::studentToDTO)
                .collect(Collectors.toList());
    }
     public Collection<StudentDTO> getAllStudent(Integer page, Integer count){
        PageRequest pageRequest = PageRequest.of(page,count);
         Collection<Student> students = studentRepository.findAll(pageRequest).getContent();
         return students.stream().map(StudentDTO::studentToDTO)
                 .collect(Collectors.toList());
     }
     public Collection<StudentDTO> getAllStudentByAgeBetween(Integer minAge, Integer maxAge){
         Collection<Student> students = studentRepository.findStudentByAgeBetween(minAge, maxAge);
        return students.stream().map(StudentDTO::studentToDTO)
                .collect(Collectors.toList());
     }
     public Collection<StudentDTO> getAllStudentByFacultyId(Long facultyId){
        Collection<Student> students = studentRepository.findStudentByFacultyId(facultyId);
        return students.stream().map(StudentDTO::studentToDTO).collect(Collectors.toList());
     }
     public Long countAllStudent(){
       return studentRepository.getCountOfStudent();
     }
      public Integer avgByAge(){
        return studentRepository.avgAgeStudent();
      }
    public  Collection<StudentDTO> getFirstStudentByAge(){
        Collection<Student> students = studentRepository.findFirstStudentByAge();
        return students.stream().map(StudentDTO::studentToDTO).collect(Collectors.toList());
    }

}

