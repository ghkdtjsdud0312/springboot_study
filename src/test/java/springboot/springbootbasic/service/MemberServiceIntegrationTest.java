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
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when(어떤 것을 검증 하는 구나 알 수 있음)
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
