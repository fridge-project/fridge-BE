package exProject.fridge.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="refresh_token")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 11)
    private int userId;

    @Column(nullable = false, length = 100)
    private String value;
}