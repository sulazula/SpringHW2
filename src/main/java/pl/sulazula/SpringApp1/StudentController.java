package pl.sulazula.SpringApp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getStudents();

        return ResponseEntity.ok(students);
    }

    @GetMapping ("/search")
    public ResponseEntity<List<Student>> searchStudentsByName(@RequestParam String name) {
        List<Student> students = studentService.getStudentByName(name);

        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping ("/group/{groupName}")
    public ResponseEntity<List<Student>> searchStudentsByGroupName(@PathVariable String groupName) {
        List<Student> students = studentService.getStudentsByGroup(groupName);

        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        studentService.addStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
    }

    @DeleteMapping ("/student/id")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        boolean removed = studentService.deleteStudentById(id);

        if (removed) {
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
