package hu.unideb.web;

import hu.unideb.model.Student;
import hu.unideb.repository.StudentRepository;
import hu.unideb.runner.StudentRunner;
import hu.unideb.util.NeptunUtils;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class StudentControllerImpl implements StudentController {

    private final StudentRepository studentRepository;


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByNeptun(@PathVariable String neptun) {
        return studentRepository.findByNeptun(neptun).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public ResponseEntity<String> download2() {
        final var headers = new HttpHeaders();
        headers.set("Content-Type", "text/csv");
        headers.set("Content-Disposition", "attachment; filename=student.csv");
        return ResponseEntity.ok()
                .headers(headers)
                .body(download());
    }

    @Override
    public void deleteStudentByNeptun(@NonNull String neptun) {
        studentRepository.deleteByNeptun(neptun);
    }

    @Override
    public Student createStudent(@NonNull Student student) {
        student.setNeptun(StudentRunner.getNeptun());
        studentRepository.createOne(student);
        return student;
    }

    @Override
    public Student updateStudent(@NonNull Student student) {
        return studentRepository.updateOne(student);
    }

    @Override
    public String download() {
        return studentRepository.findAll().stream().map(s -> s.getNeptun() + " " + s.getName()).collect(Collectors.joining("\n"));
    }

    @Override
    public Student createOne(@NonNull Student student) {
        if (student.getNeptun() == null) {
            throw new IllegalArgumentException("Neptun is required");
        }
        return  studentRepository.createOne(student);
    }
}
