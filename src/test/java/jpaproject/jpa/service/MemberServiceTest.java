//package jpaproject.jpa.service;
//
//import static org.assertj.core.api.Fail.fail;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import jakarta.persistence.EntityManager;
//import jpaproject.jpa.domain.Member;
//import jpaproject.jpa.repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@Transactional
//public class MemberServiceTest {
//
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    EntityManager em;
//
//
//    @Test
//    public void 회원가입() throws Exception {
//        //given
//        Member member = new Member();
//        member.setName("kim");
//
//        //when
//        Long saveId = memberService.join(member);
//
//        //then
//        em.flush();
//        assertEquals(member, memberRepository.findById(saveId).get());
//    }
//
//    @Test
//    public void 중복_회원_예외() throws Exception {
//        //given
//        Member member1 = new Member();
//        member1.setName("kim1");
//
//        Member member2 = new Member();
//        member2.setName("kim1");
//
//        //when
//        memberService.join(member1);
//        memberService.join(member2);
//
//        //then
//        fail("예외가 발생한다.");
//    }
//
//
//}