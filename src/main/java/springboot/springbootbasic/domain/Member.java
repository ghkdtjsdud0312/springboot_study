package springboot.springbootbasic.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    // 오라클 db는 시퀀스 쓰기도 하고 테이블에서 채번할 수도 있고 pk를 직접 넣어 줄 수도 있지만 db가 알아서 생성해주는 것이 아이덴티티라고 한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 자동으로 생성되게 해주는 코드
    private Long id; // 시스템이 저장하는 id
    private String name; // 사용자 이름

    // getter & setter 만들기
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
