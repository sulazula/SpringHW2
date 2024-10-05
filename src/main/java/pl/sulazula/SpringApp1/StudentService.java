package pl.sulazula.SpringApp1;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    List<Student> students = new ArrayList<>();

    public StudentService() {
        students.add(new Student(1l, "Bob", "A"));
        students.add(new Student(2l, "John", "B"));
        students.add(new Student(3l, "Maria", "C"));
        students.add(new Student(4l, "Anna", "B"));
        students.add(new Student(5l, "Ivan", "A"));
    }

    public void addStudent(Student student) {
        students.add(student);
    }
    public List<Student> getStudents() {
        return students;
    }
    public Optional<Student> getStudentById(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }
    public List<Student> getStudentByName(String name) {
        return students.stream()
                .filter(student -> student.getName().equals(name))
                .collect(Collectors.toList());
    }
    public List<Student> getStudentsByGroup(String groupName) {
        List<Student> studentsByGroup = students.stream()
                .filter(student -> student.getGroupName().toLowerCase().equals(groupName))
                .collect(Collectors.toList());
        return studentsByGroup;
    }
    public boolean deleteStudentById(Long id) {
        return students.removeIf(student -> student.getId().equals(id));
    }


}
