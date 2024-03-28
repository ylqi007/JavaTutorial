package com.atguigu.chap13generic.selfdefine.apply;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Description:
 *
 * @Author: ylqi007
 * @Create: 3/24/24 17:38
 */
public class DAOTest {

    @Test
    public void test1() {
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.insert(new Customer());

        List<Customer> customers = customerDAO.queryForList(1);
    }

    @Test
    public void test2() {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.insert(new Order());

        List<Order> orders = orderDAO.queryForList(1);
    }
}
