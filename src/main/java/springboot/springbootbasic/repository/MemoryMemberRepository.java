package springboot.springbootbasic.repository;

import springboot.springbootbasic.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    // store에 넣기 전에 Member에 아이디 값을 세팅해주고 이름은 setId에서 넘어온 상태
    public Member save(Member member) {
        member.setId(++sequence); // id에 세팅하고
        store.put(member.getId(), member); // store에 저장을 함
        return member; // 저장된 것을 반환해줌
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id)); // return store.get(id);결과가 없으면 null로 뜨는데 return Optional.ofNullable(store.get(id)); 하면 null이라도 optional이 감싸줘서 null도 뜰 수 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {

        // 저장된 곳에 값이 있는지 비교해보며 있으면 나오고 찾아봤는데 없으면 optional에 null이 포함되서 반환돰
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // 람다 사용(getName이 파라미터에서 넘어온 name과 같은지 확인 -> 같은 경우에 필터링 되고 찾으면 반환을 함)
                .findAny();
    }


    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values()); // store에 있는 member가 반환됨
    }

    // store를 싹 비움
    public void clearStore() {
        store.clear();
    }
}