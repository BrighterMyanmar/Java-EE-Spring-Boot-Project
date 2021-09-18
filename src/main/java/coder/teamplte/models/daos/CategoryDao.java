package coder.teamplte.models.daos;

import coder.teamplte.models.Category;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category,Integer> {
}
