package hu.unideb.web;

import hu.unideb.model.Student;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class StudentControllerImpl implements StudentController {
    @Override
    public List<Student> getAllStudents() {
        return List.of();
    }

    @Override
    public Student getStudentByNeptun(String neptun) {
        return Student.builder().neptun(neptun).name("Test").program(Student.Program.CS_Bsc)
                .created(OffsetDateTime.now().minusHours(1))
                .updated(OffsetDateTime.now()).build();
    }
}
