package com.symbolguys.stronghold.manor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.symbolguys.stronghold.manor.entity.Member;

@RepositoryRestResource(exported = false)
public interface MemberRepository extends CrudRepository<Member, Long> {
}
