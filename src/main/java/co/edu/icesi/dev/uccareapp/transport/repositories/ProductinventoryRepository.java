package co.edu.icesi.dev.uccareapp.transport.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Productinventory;


@Repository
public interface ProductinventoryRepository   extends CrudRepository<Productinventory, Integer>{

}
