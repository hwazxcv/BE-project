package org.jwt.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jwt.commons.contants.MemberType;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Base{
    @Id
    @GeneratedValue
    private Long seq;

    @Column(length = 65, unique = true , nullable = false)
    private String email;
    @Column(length = 65,nullable = false)
    private String password;
    @Column(length = 20,nullable = false)
    private String name;
    @Column(length = 15)
    private String mobile;
    @Enumerated(EnumType.STRING)
    @Column(length = 15,nullable = false)
     private MemberType type= MemberType.USER;
}
