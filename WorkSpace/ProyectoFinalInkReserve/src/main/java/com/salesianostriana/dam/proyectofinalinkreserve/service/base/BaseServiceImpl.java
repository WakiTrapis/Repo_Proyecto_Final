package com.salesianostriana.dam.proyectofinalinkreserve.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public abstract class BaseServiceImpl<T, ID, R extends JpaRepository<T, ID>> implements BaseService<T, ID> {

	protected final R repository;
	
	public BaseServiceImpl(R repository) {
        this.repository = repository;
    }
	
	
	public List<T> findAll(){
		return repository.findAll();
	}
	
	@Override
	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}
	
	@Override
	public T save(T t) {
		return repository.save(t);
	}
	
	@Override
	public T edit(T t) {
		return repository.save(t);
	}
	
	@Override
	public void delete(T t) {
		repository.delete(t);
	}
	
	@Override
	public void deleteById(ID id) {
		repository.deleteById(id);
	}
	
}
