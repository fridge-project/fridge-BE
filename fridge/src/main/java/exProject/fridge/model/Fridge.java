package exProject.fridge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@IdClass(FridgeId.class)
public class Fridge {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // 외래키 두개 합쳐서 PK로 변경
//    private int id;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user; // 사용자 id - 외래키

    @Id
    @ManyToOne
    @JoinColumn(name = "ingre_id", referencedColumnName = "id")
    Ingredient ingredient; // 재료코드 - 외래키

    @Column(length = 30) // 날짜로 변경해야함
    private String exp; // 유통기한 - null 가능

    @Column(length = 30) // 날짜로 변경해야함 // erd다이어그램 추가
    private String addDate; // 등록날짜

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StorageType storage; // 보관 방법

    @Column(length = 100)
    private String memo; // 메모

}