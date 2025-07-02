package br.com.gutendex.entity.repository;

import br.com.gutendex.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
