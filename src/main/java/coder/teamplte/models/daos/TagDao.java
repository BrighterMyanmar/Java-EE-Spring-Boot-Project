package coder.teamplte.models.daos;

import coder.teamplte.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TagDao extends CrudRepository<Tag,Integer> {

}
