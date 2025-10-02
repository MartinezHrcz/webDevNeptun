package hu.unideb.web;

import hu.unideb.model.Student;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface StudentController {
    /*@RequestMapping
            (
                    method = RequestMethod.GET,
                    path = "/api/student"
            )
            same as
            |
            |
           \-/
            ˘
     */
    @GetMapping(path = "/api/student/download",
    produces = "text/csv") // fontos a sorrend
    String download();
    @GetMapping("/api/student/download-v2")
    ResponseEntity<String> download2();
    @GetMapping("/api/student")
    List<Student> getAllStudents();
    @GetMapping("/api/student/{neptun}")
    Student getStudentByNeptun(@PathVariable String neptun);
    @DeleteMapping("/api/student/{neptun}")
    void deleteStudentByNeptun(@PathVariable @NonNull String neptun);
    @PostMapping("/api/student")
    Student createStudent(@NonNull @RequestBody Student student);
    @PutMapping("/api/student")
    Student updateStudent(@NonNull @RequestBody Student student);
    @PutMapping("/api/student/create")
    Student createOne(@NonNull @RequestBody Student student);

}
