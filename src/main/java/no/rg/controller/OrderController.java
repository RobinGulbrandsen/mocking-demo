package no.rg.controller;

import no.rg.domain.Order;
import no.rg.domain.User;
import no.rg.repository.OrderRepository;
import no.rg.repository.UserRepository;

/**
 *
 * @author robin
 */
public class OrderController {
    
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    
    /**
     * Constructor used for testing.
     * 
     * @param userRepository  {@link UserRepository}
     * @param orderRepository {@link OrderRepository}
     */
    public OrderController(final UserRepository userRepository, 
                           final OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }
    
    /**
     * Adds a new order, requires user to be logged in
     * @param order  {@link Order} to be persisted
     * @return order {@link Order} with generated values
     */
    public Order addOrder(Order order) {
        User current = userRepository.getCurrentUser();
        if(current == null) {
            throw new RuntimeException("User is not loged in");
        }
        
        return orderRepository.create(order);
    }
    
}
