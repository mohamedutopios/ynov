package com.example.ynovv.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 15, message = "Le nom ne doit pas être supeieur à 15")
    @NotBlank(message =" L'auteur doit avoir un nom")
    @Size(min = 3,message = "Le nom doit être plus grand que 3")
    private String name;

    @NotBlank(message = "L'email ne peut être vide")
    @Email(message = "Un email valide" )
    @Pattern(regexp = ".*\\.com$", message = "L'email doit se terminer par .com")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<>();
/*
    @OneToMany(mappedBy = "author",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();*/

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
