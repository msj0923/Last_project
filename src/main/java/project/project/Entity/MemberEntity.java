package project.project.Entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class MemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_num; //유저 숫자

    @Column(nullable = false, length = 30, unique = true)
    private String member_username; //아이디

    @Column(nullable = false)
    private String member_nickname;

    @Column(nullable = false, length = 30)
    private String member_password;

    @Column(nullable = false,length = 50)
    private String member_email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /*회원 정보 수정*/
    public void modify(String member_nickname, String member_password){
        this.member_nickname = member_nickname;
        this.member_password = member_password;
    }

    public MemberEntity updateModifiedDate(){
        this.onPreUpdate();
        return this;
    }

    public String getRoleValue(){
        return this.role.getValue();
    }




}
