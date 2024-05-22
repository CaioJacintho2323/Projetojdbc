package application;


import model.dao.Daofactory;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;
import db.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Departament d1 = new Departament(1, "livros");
        Seller sc = new Seller(21, "bob", "bob@gmail", new Date(),3000.0,d1);
        System.out.println(sc);

        SellerDao sellerDao = Daofactory.createSellerDao();
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
    }
}