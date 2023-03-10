package ru.hogwarts.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@Data
@EqualsAndHashCode
public class FacultyDTO {
    private Long id;
    private String name;
    private String color;
    @JsonIgnore
    private Collection<Student> students;
    @JsonIgnore
    private Long StudentId;

    public static FacultyDTO facultyToDTO(Faculty faculty){
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setId(faculty.getId());
        facultyDTO.setName(faculty.getName());
        facultyDTO.setColor(faculty.getColor());
        return facultyDTO;
    }
    public Faculty toFaculty(){
        Faculty faculty = new Faculty();
        faculty.setId(this.getId());
        faculty.setName(this.getName());
        faculty.setColor(this.getColor());
        return faculty;
    }
}
