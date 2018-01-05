package org.sqribe.api.module.user.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.sqribe.api.module.user.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
