package com.jamesdizon.ecommercestore.service;

import com.jamesdizon.ecommercestore.model.LocalUser;
import com.jamesdizon.ecommercestore.model.WebOrder;
import com.jamesdizon.ecommercestore.model.dao.WebOrderDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private WebOrderDAO webOrderDAO;

    public OrderService(WebOrderDAO webOrderDAO){
        this.webOrderDAO = webOrderDAO;
    }

    public List<WebOrder> getOrders(LocalUser user){
        return webOrderDAO.findByUser(user);
    }
}
