package study.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        //Team 영속성 컨텍스트에 저장
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamA);
        Member member4 = new Member("member4", 40, teamA);

        //Member 영속성 컨텍스트에 저장
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        //DB에 쿼리를 날림
        em.flush();
        //영속성 컨텍스트에 데이터 비움
        em.clear();

        List<Member> members = em.createQuery("select m from Member m ", Member.class).getResultList();

        for(Member m : members) {
            System.out.println("member = " + m);
            System.out.println("--> member.team = " + m.getTeam());
        }
    }


}