package com.vn.bosch.jpa.persistence.dao;

import java.util.List;

import com.vn.bosch.jpa.persistence.model.Foo;

public interface IFooDao {

    Foo findOne(long id);

    List<Foo> findAll();

    void create(Foo entity);

    Foo update(Foo entity);

    void delete(Foo entity);

    void deleteById(long entityId);

}
