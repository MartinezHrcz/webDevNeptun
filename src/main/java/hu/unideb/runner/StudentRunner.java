package hu.unideb.runner;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import hu.unideb.model.Student;
import hu.unideb.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class StudentRunner implements CommandLineRunner {

    private static final Random RANDOM = new Random();
    private static final Faker FAKER = new Faker();
    private static final String LETTER = "ABCDEFGHJKLVNOPXYWZ";
    private static final String DIGITS = "0123456789";
    private static final Logger logger = LoggerFactory.getLogger(StudentRunner.class);

    private final StudentRepository studentRepository;

    public static String getNeptun() {
        return IntStream.range(0,6)
                .mapToObj(pos -> pos == 0
                    ? LETTER.charAt(RANDOM.nextInt(LETTER.length()))
                                :(LETTER + DIGITS).charAt(
                                        RANDOM.nextInt((LETTER+DIGITS).length())
                                )
                        )
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running StudentRunner");
        for (int i = 0; i < 100; i++) {
            Name name = FAKER.name();
            Student student = Student.builder()
                    .neptun(getNeptun())
                    .name(name.firstName()+ " " + name.lastName())
                    .program(Student.Program.random())
                    .build();
            // a perzisztentált obj-al dolgozunk tovább
            student = studentRepository.createOne(student);
            logger.info(student.toString());
        }
        logger.info("count:{}", studentRepository.findAll().size());

    }


}
