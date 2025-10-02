package hu.unideb.repository;

import hu.unideb.model.Student;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final Map<String, Student> students;

    public StudentRepositoryImpl() {
        students = new HashMap<>();
    }

    @Override
    public List<Student> findAll() {
        return students.values()
                .stream()
                .sorted(Comparator.comparing(Student::getNeptun))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Student> findByNeptun(@NonNull String neptun) {
        return Optional.ofNullable(students.get(neptun));
    }

    @Override
    public Student createOne(@NonNull Student student) {
        student.setCreated(OffsetDateTime.now());
        student.setUpdated(OffsetDateTime.now());
        students.put(student.getNeptun(), student);
        return student;
    }

    @Override
    public Student updateOne(@NonNull Student student) {
        return null;
    }

    @Override
    public void deleteByNeptun(@NonNull String neptun) {

    }
}
