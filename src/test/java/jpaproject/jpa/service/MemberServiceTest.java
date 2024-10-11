package jpaproject.jpa.service;

import jpaproject.jpa.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test

    public void 회원가입() throws Exception {
        //given

        //when

        //then
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given

        //when

        //then
    }


}