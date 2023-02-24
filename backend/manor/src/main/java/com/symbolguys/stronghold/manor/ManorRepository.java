package com.symbolguys.stronghold.manor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.symbolguys.stronghold.manor.entity.Manor;

@RepositoryRestResource(collectionResourceRel = "manors", path = "manors")
public interface ManorRepository extends CrudRepository<Manor, Long> {
}
