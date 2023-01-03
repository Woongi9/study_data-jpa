package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

//    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);
    //@Param을 명확하게 JPQL을 명확하게 작성했을 경우 사용
    //Member 엔티티의 NamedQuery를 찾아서 실행
    //JpaRepository 의 엔티티의 NamedQuery가 있는지 먼저 찾고 있으면 Query문 없이도 실행 가능
    //없으면 메서드를 findByUsernameAndAgeGreaterThan 처럼 만들어 실행

}
