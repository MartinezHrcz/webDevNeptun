package hu.unideb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student {
    @EqualsAndHashCode.Include
    private String neptun;

    private String name;
    Program program;
    private OffsetDateTime created;
    private OffsetDateTime updated;


    public enum Program {
        @JsonProperty("Programtervező informatikus")
        CS_Bsc,
        CSE_Bsc,
        BI_Bsc
    }
}
