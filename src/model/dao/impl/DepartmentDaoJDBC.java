package model.dao.impl;

import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Departament;
import model.entities.Seller;

import java.sql.*;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Departament obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO seller"
                            + " (Name)"
                            + "VALUES"
                            + "(?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());

            int rows = st.executeUpdate();

            if (rows > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            } else {
                throw new DbException("Erro inesperado");
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public void update(Departament obj) {

    }

    @Override
    public void deletebyId(Integer id) {

    }

    @Override
    public Departament findById(Integer id) {
        return null;
    }

    @Override
    public List<Departament> findAll() {
        return null;
    }
}
