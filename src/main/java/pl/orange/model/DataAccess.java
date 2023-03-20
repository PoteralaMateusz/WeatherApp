package pl.orange.model;

import java.util.List;

public interface DataAccess <E,ID>{

    void save(E entity);
    List<E> findAll();

    void deleteByID(ID id);
}
