package hello.hellospring.domain;

import jdk.jfr.Enabled;

import javax.persistence.*;

//jpa가 관리하는 Entity라고 매핑해줌
@Entity
public class Member {

    @Id
    // name값을 입력하면 db를 통해 id값이 자동 생성되도록 설정(identity)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //시스템이 정하는 임의의 값, id

    @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
