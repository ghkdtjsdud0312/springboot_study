package springboot.springbootbasic.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springboot.springbootbasic.domain.Member;
import springboot.springbootbasic.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
//    MemberService memberService = new MemberService();
    MemberService memberService;
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 각각 테스트 코드 실행할 때마다 실행되고 다음 테스트 코드 넘어갈 때 DB의 값을 모두 지워 주는 코드
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    // 테스트에서는 코드에 한글 가능
    @Test
    void 회원가입() {
        // given(어떤 데이터를 기반으로 하는구나 알 수 있음)
        Member member = new Member();
        member.setName("hello");

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

        // when(어떤 것을 검증 하는구나 알 수 있음)
            memberService.join(member1);
            assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then(여기가 이제 검증부구나 알 수 있음)

    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}
