package coder.teamplte.models.daos;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import coder.teamplte.models.Childcat;

@Repository
@Transactional
public interface ChildcatDao extends CrudRepository<Childcat, Integer> {

}
