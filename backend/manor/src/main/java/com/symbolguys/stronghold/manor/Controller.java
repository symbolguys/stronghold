package com.symbolguys.stronghold.manor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.symbolguys.stronghold.manor.entity.Manor;
import com.symbolguys.stronghold.manor.entity.Member;

@RestController
public class Controller {

    @Autowired
    public ManorRepository manorRepository;

    @Autowired
    public MemberRepository memberRepository;

    @GetMapping("/get-manors")
	public Iterable<Manor> allManors() {
        return manorRepository.findAll();
    }

    @GetMapping("/get-members")
	public Iterable<Member> allMembers() {
        return memberRepository.findAll();
    }

}
