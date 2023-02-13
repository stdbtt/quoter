package stdbtt.quoter.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stdbtt.quoter.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByName(String name);
}
