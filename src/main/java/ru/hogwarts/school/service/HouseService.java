package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class HouseService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private Long generatedId = 0L;

    public Faculty createFaculty(Faculty faculty) {
       faculty.setId(++generatedId);
       faculties. put(generatedId, faculty);
       return faculty;
    }

    public Faculty getFacultyById(Long id) {
        return faculties.get(id);
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }
    public Faculty deleteFaculty(Long id) {
        return faculties.remove(id);
    }
    public Collection<Faculty> getAllFacultyByColor(String color){
        Collection<Faculty> facultyByColor = faculties.values();
        return   facultyByColor.stream().filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
    public Collection<Faculty> getAllFaculty(){
        return faculties.values();
    }
}
