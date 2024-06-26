package springboot.springbootbasic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.springbootbasic.domain.Member;
import springboot.springbootbasic.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service // 서비스가 뜨면 Autowired가 있으면 멤버 리포지토리가 필요하겠구나 해서 스프링 컨테이너에 있는 메모리멤버 리포지토리를 넣어줌
// service는 연결 해줘야 함
public class MemberService {

    // 원래 코드는 private final MemberRepository memberRepository = new MemoryMemberRepository;
    // 이지만 test 코드의 MemoryMemberRepository memberRepository = new MemoryMemberRepository();의 DB 공간이 달라질 수 있으므로 밑에 코드처럼 바꿔준다.
    private final MemberRepository memberRepository;

    // 외부에서 넣어주게 바꿈
    @Autowired // 멤버 서비스는 멤버 리포지토리가 필요
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {

        long start = System.currentTimeMillis();

        try {
            // 조건1 : 같은 이름의 중복 회원이 있으면 안된다.
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member); // member 내용 저장
            return member.getId(); // id 반환
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join " + timeMs + "ms");
        }
    }
    // 단축키: control + T -> Extra Method(validateDuplicateMember 이름 바꾸기)
    // 중복 회원 검증
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // 값이 있으면 이 로직이 동작함(예외처리 내용) -> optional에 한번 감싸서 ifpresent 반횐 해주고 optional이기 때문에 가능
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {

        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll(); // 로직이 List<Member> 이기 때문에 return 반환문
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
