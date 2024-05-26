package springboot.springbootbasic.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springboot.springbootbasic.domain.Member;
import springboot.springbootbasic.repository.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// 애노테이션을 테스트 케이스에 딱 달면 테스트를 실행할 때 트랜잭션을 먼저 실행하고 그 다음에 DB에 데이터를 insert 쿼리를 하고 다 넣은 다음에 테스트가 끝나면 롤백을 해준다.
// 그래서 DB에 넣었던 데이터가 다 깔끔하게 반영이 안 되고 다 지워진다.
// 즉, 다음 데이터를 또 반복해서 실행할 수 있다는 뜻이다.
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given(어떤 데이터를 기반으로 하는구나 알 수 있음)
        Member member = new Member();
        member.setName("spring");

        // when(어떤 것을 검증 하는구나 알 수 있음)
        Long saveId = memberService.join(member);

        // then(여기가 이제 검증부구나 알 수 있음)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    // 예외 흐름 코드 작성
    @Test
    public void 중복_회원_예외() {
        // given(어떤 데이터를 기반으로 하는구나 알 수 있음)
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

        // when(어떤 것을 검증 하는 구나 알 수 있음)
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
