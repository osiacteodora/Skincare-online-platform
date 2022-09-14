package com.example.demo.Repository;

import com.example.demo.DataModels.User;
import com.example.demo.Repository.Connection.Connection;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final java.sql.Connection context = Connection.getConnection();

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statementString =context.prepareStatement("Select * from users");
            ResultSet result = statementString.executeQuery();
            while(result.next()){
                users.add(new User(
                        result.getLong("id_user"),
                        result.getString("nume"),
                        result.getString("prenume"),
                        result.getString("type"),
                        result.getString("email"),
                        result.getInt("buget"),
                        result.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }

    public User addUser(User user){
        try {
            PreparedStatement statementString = context.prepareStatement("insert into users values(?,?,?,?,?,?,?)");
            statementString.setString(1, Long.toString(user.getId_user()));
            statementString.setString(2,user.getNume());
            statementString.setString(3,user.getPrenume());
            statementString.setString(4,user.getType());
            statementString.setString(5,user.getEmail());
            statementString.setString(6,Integer.toString(user.getBudget()));
            statementString.setString(7,user.getPassword());
            statementString.executeUpdate();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return user;
        }
    }

    public int getAvailableUserId(){
        try {
            PreparedStatement statementString = context.prepareStatement("select id_user from Users");
            ResultSet result = statementString.executeQuery();
            Long id_curent = Long.valueOf(0);
            while(result.next()){
                if (result.getLong("id_user") > id_curent) {
                    id_curent = result.getLong("id_user");
                };
            }
            return (int) (id_curent + 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void UpdateUser(User user){
        try {
            PreparedStatement statementString = context.prepareStatement("update users set nume = ?, prenume = ?, type = ?, email = ?, buget = ?, password = ? where id_user = ?");
            statementString.setString(7,Long.toString(user.getId_user()));
            statementString.setString(1,user.getNume());
            statementString.setString(2,user.getPrenume());
            statementString.setString(3,user.getType());
            statementString.setString(4, user.getEmail());
            statementString.setString(5,Integer.toString(user.getBudget()));
            statementString.setString(6,user.getPassword());
            statementString.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User findUserById(Integer id) {
        try {
            PreparedStatement statementString = context.prepareStatement("select * from users where id_user=?");
            User found = null;
            statementString.setInt(1, id);
            ResultSet result = statementString.executeQuery();
            if (result.next()) {
            found = new User(
                    result.getLong("id_user"),
                    result.getString("nume"),
                    result.getString("prenume"),
                    result.getString("type"),
                    result.getString("email"),
                    result.getInt("buget"),
                    result.getString("password"));
            }
            return found;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void DeleteUser(Long id_user) {
        try {
            PreparedStatement statementString = context.prepareStatement("delete from users where id_user=?");
            statementString.setString(1,Long.toString(id_user));
            statementString.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public User getUser(User user) {
        try {
            PreparedStatement statementString = context.prepareStatement("select * from Users where email = ? and password = ?");
            statementString.setString(1, user.getEmail());
            statementString.setString(2, user.getPassword());
            ResultSet result = statementString.executeQuery();

            while(result.next()){
                user.setType(result.getString("type"));
                user.setId_user(result.getLong("id_user"));
                return user;
            }
            user.setType("Unauthorized");
            return user;
        } catch (SQLException e) {
            user.setType("Unauthorized");
            return user;
        }
    }
}
