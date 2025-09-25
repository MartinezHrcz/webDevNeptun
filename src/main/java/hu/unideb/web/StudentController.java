package hu.unideb.web;

import hu.unideb.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    @GetMapping("/api/student")
    List<Student> getAllStudents();
    @GetMapping("/api/student/{neptun}")
    Student getStudentByNeptun(@PathVariable String neptun);

}
