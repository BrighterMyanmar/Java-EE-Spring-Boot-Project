package coder.teamplte.models.daos;

import coder.teamplte.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category,Integer> {
}
