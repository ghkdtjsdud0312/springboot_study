package springboot.springbootbasic.repository;

import springboot.springbootbasic.domain.Member;

import java.util.List;
import java.util.Optional;

// 회원 저장소
public interface MemberRepository {
    Member save(Member member); // save는 member를 저장되는 것
    // Optional은 null로 처리하는 방법에서 Optional로 감싸서 반환하는 방법으로 많이 사용
    Optional<Member> findById(Long id); // findById는 시스템에 있는 id를 식별할 수 있음
    Optional<Member> findByName(String name); // findByName하면 이름을 찾아 올 수 있음
    List<Member> findAll(); // 지금까지 저장되어 있는 모든 회원 정보를 반환
}
