package com.ujiuye.cust.service;

import com.ujiuye.cust.bean.Customer;

import java.util.List;

public interface CustomerService {
    public void saveInfo(Customer customer);

    List<Customer> getCustomerList();

    Customer getCustomerDetail(Integer cid);

    void updateCustomer(Customer customer);

    boolean batchDelete(Integer[] ids);

    List<Customer> search(Integer cid, String keyword, Integer orderby);

    void batchInsert(List<Customer> customers);
}
