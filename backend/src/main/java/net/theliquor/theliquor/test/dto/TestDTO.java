package net.theliquor.theliquor.test.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "test")
@Getter
@Setter
public class TestDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public TestDTO() {

    }

    public TestDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
