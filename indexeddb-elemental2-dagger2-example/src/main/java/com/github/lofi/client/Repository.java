package com.github.lofi.client;

import java.util.Optional;
import java.util.Set;

interface Repository<T> {

	Optional<T> get(String id);

	Set<T> get();

	void persist(T entity);

	void remove(T entity);

}
