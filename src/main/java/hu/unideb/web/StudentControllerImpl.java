package hu.unideb.web;

import hu.unideb.model.Student;
import hu.unideb.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentControllerImpl implements StudentController {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByNeptun(String neptun) {
        return studentRepository.findByNeptun(neptun).orElseThrow(IllegalArgumentException::new);
    }
}
