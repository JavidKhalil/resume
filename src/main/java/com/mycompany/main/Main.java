package com.mycompany.main;

import com.mycompany.DAO.impl.UserDAOImpl;
import com.mycompany.main.bean.User;

public class Main {

    public static void main(String[] args) throws Exception {

        UserDAOImpl userdao = new UserDAOImpl();

        User u = userdao.getById(1);
        u.setName("Qaqa");
        userdao.updateUser(u);
        
//        User u = new User(); 
//        u.setId(1);
//        u.setName("Filankes");
//        u.setPhone("0556890358");
//        u.setEmail("email@maik.cim");
//        System.out.println(userdao.updateUser(u));
//        List<User> list = userdao.getAll();
//        System.out.println("list " + list);
    }

}
