package coder.teamplte.models.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import coder.teamplte.models.Tag;

@Repository
@Transactional
public interface TagRepository extends CrudRepository<Tag, Integer> {
   @Override
   List<Tag> findAll();
}
