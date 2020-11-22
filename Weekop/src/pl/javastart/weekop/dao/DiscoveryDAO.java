package pl.javastart.weekop.dao;

import java.util.List;

import pl.javastart.weekop.model.Discover;

public interface DiscoveryDAO extends GenericDAO<Discover, Long>{

	List<Discover> getAll();
}
