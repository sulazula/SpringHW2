package pl.sulazula.SpringApp1;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentServiceTest {
	@Autowired
	private StudentService studentService;

	@Test
	public void getStudentsByGroup() {
		String groupName = "abc";
		List<Student> expected = new ArrayList<>();
		List<Student> actual = studentService.getStudentsByGroup(groupName);

		assertEquals(expected, actual);
	}
}
