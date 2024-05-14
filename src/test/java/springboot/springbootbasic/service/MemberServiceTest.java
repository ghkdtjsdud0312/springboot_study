package springboot.springbootbasic.service;

import org.junit.jupiter.api.Test;
import springboot.springbootbasic.domain.Member;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService = new MemberService();

    @Test
    // 회원가입
    void join() {
        // given(어떤 데이터를 기반으로 하는구나 알 수 있음)
        Member member = new Member();
        member.setName("hello");

        // when(어떤 것을 검증 하는구나 알 수 있음)
        Long saveId = memberService.join(member);

        // then(여기가 이제 검증부구나 알 수 있음)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}
