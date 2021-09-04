package coder.teamplte.models.daos;

import coder.teamplte.models.Subcat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SubcatDao extends CrudRepository<Subcat,Integer> {
}
