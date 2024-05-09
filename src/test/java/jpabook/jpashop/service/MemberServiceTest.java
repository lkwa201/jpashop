package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	@Autowired EntityManager em;

	@Test
	public void 회원가입() throws Exception {
		Member member = new Member();
		member.setName("lee");

		Long join = memberService.join(member);

		assertEquals(member, memberRepository.findOne(join));
	}

	@Test(expected = IllegalStateException.class)
	public void 중복회원예외() throws Exception {
		Member member1 = new Member();
		member1.setName("lee");

		Member member2 = new Member();
		member2.setName("lee");

		memberService.join(member1);
		memberService.join(member2);

		//then
		fail("예외가 발생해야 한다.");
	}

}