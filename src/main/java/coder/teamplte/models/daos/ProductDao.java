package coder.teamplte.models.daos;


import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import coder.teamplte.models.Product;

@Repository
@Transactional
public interface ProductDao extends CrudRepository<Product, Integer> {

}
