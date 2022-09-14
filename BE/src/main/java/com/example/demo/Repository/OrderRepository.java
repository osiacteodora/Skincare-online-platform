package com.example.demo.Repository;

import com.example.demo.DataModels.Order;
import com.example.demo.Repository.Connection.Connection;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private final java.sql.Connection context = Connection.getConnection();

    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement statementString = context.prepareStatement("select * from Orders");
            ResultSet result = statementString.executeQuery();
            while (result.next()) {
                orders.add(new Order(result.getLong("order_Id"),
                        result.getLong("user_Id")
                ));
                PreparedStatement preparedStatement = context.prepareStatement("select * from order_utility where order_id=?");
                preparedStatement.setLong(1, orders.get(orders.size() - 1).getOrder_Id());
                ResultSet resultSet = preparedStatement.executeQuery();

                List<Long> productIds = new ArrayList<>();
                List<Integer> quanitites = new ArrayList<>();

                while (resultSet.next()) {
                    productIds.add(resultSet.getLong("product_id"));
                    quanitites.add(resultSet.getInt("quantity"));
                }
                orders.get(orders.size() - 1).setProductIds(productIds);
                orders.get(orders.size() - 1).setQuantities(quanitites);
            }

            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addOrder(Order order) {
        try {
            PreparedStatement statementString = context.prepareStatement("insert into Orders values(?,?)");
            statementString.setString(1, Long.toString(order.getOrder_Id()));
            statementString.setString(2, Long.toString(order.getUser_Id()));
            statementString.executeUpdate();
            for(int i = 0; i < order.getProductIds().size(); i++) {
                PreparedStatement preparedStatement = context.prepareStatement("insert into order_utility values(?, ?, ?)");
                preparedStatement.setLong(1, order.getOrder_Id());
                preparedStatement.setLong(2, order.getProductIds().get(i));
                preparedStatement.setLong(3, order.getQuantities().get(i));
                preparedStatement.executeUpdate();
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public int getAvailableOrderId() {
        try {
            PreparedStatement statementString = context.prepareStatement("select order_Id from Orders");
            ResultSet result = statementString.executeQuery();
            Long idCurrent = Long.valueOf(0);
            while (result.next()) {
                idCurrent++;
            }
            return (int) (idCurrent + 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public void UpdateOrder(Order order){
        try {
            PreparedStatement statementString = context.prepareStatement("update orders set quantity = ?,user_Id = ?,product_Id = ? where order_Id=?");
            statementString.setString(4,Long.toString(order.getOrder_Id()));
            statementString.setString(2,Long.toString(order.getUser_Id()));
            statementString.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteOrder(Long order_Id) {
        try {
            PreparedStatement statementString = context.prepareStatement("delete from orders where order_Id=?");
            statementString.setString(1,Long.toString(order_Id));
            statementString.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}



