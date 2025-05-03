package kg.attractor.javasharehub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usr_file",
            joinColumns = @JoinColumn(name = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "usr_id")
    )
    private List<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(name = "file_name")
    private String fileName;
    private String status;

}
