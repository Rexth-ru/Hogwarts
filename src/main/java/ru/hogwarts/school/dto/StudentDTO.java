package ru.hogwarts.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

@Data
@EqualsAndHashCode
public class StudentDTO {
    private Long id;
    private String name;
    private Integer age;
    private Long facultyId;
    @JsonIgnore
    private Faculty faculty;


    public static StudentDTO studentToDTO(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setAge(student.getAge());
        studentDTO.setFacultyId(student.getFaculty().getId());
        return studentDTO;
    }
    public Student toStudent(){
        Student student = new Student();
        student.setId(this.getId());
        student.setName(this.getName());
        student.setAge(this.getAge());
        return student;
    }
}
