package br.com.four.ecom.core.infrastructure.database.repositories;

import br.com.four.ecom.core.infrastructure.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, String> {
}
