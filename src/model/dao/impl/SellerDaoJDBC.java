package model.dao.impl;

import db.DbException;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deletebyId(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st=conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department"
                    + "ON seller.DepartmentId = department.Id"
                    + "WHERE seller.Id = ?");

            st.setInt(1,id);
            rs= st.executeQuery();
            if(rs.next()){
                Departament dep = intantiateDepartment(rs);
                Seller obj = intantiateSeller(rs,dep);
                return obj;
            }
            return null;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    private Seller intantiateSeller(ResultSet rs, Departament dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartament(dep);
        return obj;
    }

    private Departament intantiateDepartment(ResultSet rs) throws SQLException {
        Departament dep = new Departament();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    @Override
    public List<Seller> findByDepartment(Departament departament) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st=conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name");

            st.setInt(1,departament.getId());
            rs= st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer,Departament> map = new HashMap<>();
            while (rs.next()){
                Departament dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null) {
                    dep = intantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"),dep);
                }
                Seller obj = intantiateSeller(rs,dep);
                list.add(obj);
            }
            return list;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
}
