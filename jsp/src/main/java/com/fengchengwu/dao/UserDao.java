package com.guoguochen.dao;

import com.guoguochen.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserDao implements  IUserDao{
    @Override
    public boolean saveUser(Connection con, User user) throws SQLException {
        //insert
        return false;
    }

    @Override
    public int deleteUser(Connection con, User user) throws SQLException {
        //delete a row
        return 0;
    }

    @Override
    public int updateUser(Connection con, User user) throws SQLException {
        //update a row
        return 0;
    }

    @Override
    public User findById(Connection con, Integer id) throws SQLException {
        //select
        return null;
    }

    @Override
    public User findByUsernamePassword(Connection con, String username, String password) throws SQLException {
        //use for login
        //jdbc code
        String sql="select id,username,password,email,gender,birthdate from usertable where username='"+username+"' and password='"+password+"'";

        ResultSet rs =con.createStatement().executeQuery(sql);
        User user = null;
        if (rs.next()){//login success
            //set data into user model
            user = new User();//model
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString(username));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthdate"));
        }
        return user;
    }

    @Override
    public List<User> findByUsername(Connection con, String username) throws SQLException {
        return null;
    }

    @Override
    public List<User> findByPassword(Connection con, String password) throws SQLException {
        return null;
    }

    @Override
    public List<User> findByEmail(Connection con, String email) throws SQLException {
        return null;
    }

    @Override
    public List<User> findByGender(Connection con, String gender) throws SQLException {
        return null;
    }

    @Override
    public List<User> findByBirthdate(Connection con, Date birthDate) throws SQLException {
        return null;
    }

    @Override
    public List<User> findAllUser(Connection con) throws SQLException {
        return null;
    }
}
