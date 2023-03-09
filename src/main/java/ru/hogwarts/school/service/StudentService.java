package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private Long generatedStudentId = 0L;

    public Student createStudent(Student student) {
        student.setId(++generatedStudentId);
        students. put(generatedStudentId, student);
        return student;
    }

    public Student getStudentById(Long id) {
        return students.get(id);
    }

    public Student updateStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }
    public Student deleteStudent(Long id) {
        return students.remove(id);
    }
    public Collection<Student> getAllStudentByAge(Integer age){
      Collection<Student> studentByAge = students.values();
        return   studentByAge.stream().filter(student -> student.getAge().equals(age))
                .collect(Collectors.toList());
    }
     public Collection<Student> getAllStudent(){
        return students.values();
     }
}

