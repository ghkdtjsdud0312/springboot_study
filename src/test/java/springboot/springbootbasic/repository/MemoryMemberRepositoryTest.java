package springboot.springbootbasic.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import springboot.springbootbasic.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // Test 코드 실행 하고 나면 Test 한 것은 지워 줘야 하기 때문에 이 코드를 넣어주어야 한다.
    // Test 코드 실행 메서드가 끝날 때마다 1번씩 저장소를 지운다. 그러면 순서와 상관 없이 테스트 내용들이 실행된다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // test는 서로 의존 관계 없이 실행이 되어야 환다. 그러기 위해서는 하나의 test가 끝날 때마다 저장소나 공용 데이터들을 다 지워 줘야 문제 없이 작동 한다.
    @Test
    // 저장이 잘 되는지 테스트 해보기
    public void save() {
        Member member = new Member(); // 저장한 것
        member.setName("spring"); // 이름 예로 'spring'으로 넣어보기

        repository.save(member); // 저장해보기

        // 검증 해보기, DB에서 꺼낸 것
        Member result = repository.findById(member.getId()).get(); // 테스트 코드에서는 Optional에서 꺼낼 때는 .get()으로 꺼내기
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
