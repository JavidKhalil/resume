/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.DAO.impl;

import com.mycompany.main.DAO.inter.AbstractDAO;
import com.mycompany.main.DAO.inter.UserDAOInter;
import com.mycompany.main.bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class UserDAOImpl extends AbstractDAO implements UserDAOInter {

    /* Connection obyektini iw qurtarandan sonra close() etmek lazimdir 
    * o close edilende ResultSet ve Statement avtomatik olaraq close edilir 
    * amma bunun yerine try with resources istifade etmek olar 
     */
    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            stmt.execute("select * from user");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                result.add(new User(id, name, surname, phone, email));
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection conn = connect()) {
            //preparedstatement ? iwaresi olan yerlere stringden evvel / iwaresi qoyur ve belelikle 
            //encode edir yeni burda her hansi bir kod yazilsa bele hemin kodu bir text-e cevirir 
            PreparedStatement ps = conn.prepareStatement("update user set name = ?, surname = ?, phone = ?, email = ? where id = ?");
            //preparedstatement ? iwarelerinden istifade edilir 
            //burada ? iwareleri nomrelenir eger n-ci iware int-dirse setInt(n, reqem) yazilir
            //eger ? iwaresi n-ci Stringdirse onda setString(n, str); yazilir 
            //preparedstatement Character Encode edir yeni / iwaresi elave edir sql-de sutun adlarindan evvel  
            ps.setString(1, u.getName());
            ps.setString(2, u.getSurname());
            ps.setString(3, u.getPhone());
            ps.setString(4, u.getEmail());
            ps.setInt(5, u.getId());
            return ps.execute();
        } catch (Exception ex) {
            ex.getMessage();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            return stmt.execute("delete from user where id = " + id);
            //eger where id = neise burada neise int-dirse onda yuxaridaki kimi yazmaq olar
//            return stmt.execute("delete from user where id = 1");
        } catch (Exception ex) {
            ex.getMessage();
            return false;
        }

    }

}
