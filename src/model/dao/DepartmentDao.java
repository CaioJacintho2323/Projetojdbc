package model.dao;

import model.entities.Departament;

import java.util.List;

public interface DepartmentDao {
    void insert(Departament obj);
    void update(Departament obj);
    void deletebyId(Integer id);
    Departament findById(Integer id);
    List<Departament> findAll();

}
