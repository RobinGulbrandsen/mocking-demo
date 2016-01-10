package no.rg.controller;

import no.rg.domain.Order;
import no.rg.domain.User;
import no.rg.repository.OrderRepository;
import no.rg.repository.UserRepository;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author robin
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
    
    @Mock
    private UserRepository userRepository;
    @Mock
    private OrderRepository orderRepository;
    
    private OrderController orderController;
    
    @Before
    public void setup() {
        //Passing the mocked interfaces to the controller
        orderController = new OrderController(userRepository, orderRepository);
    }
    
    @Test
    public void addOrder_userIsLoggedIn_createsANewOrder() {
        //Arrange
        when(userRepository.getCurrentUser()).thenReturn(new User());
        Order order = new Order("new order");
        when(orderRepository.create(order)).thenReturn(order);
                
        //Act
        Order newOrder = orderController.addOrder(order);
        
        //Assert
        assertEquals(order, newOrder);
    }
    
    @Test
    public void addOrder_userIsLoggedIn_createsANewOrderVerify() {
        //Arrange
        when(userRepository.getCurrentUser()).thenReturn(new User());
        Order order = new Order("new order");
        when(orderRepository.create(order)).thenReturn(order);
                
        //Act
        Order newOrder = orderController.addOrder(order);
        
        //Assert
        Mockito.verify(orderRepository).create(order);
    }
    
    @Test(expected = RuntimeException.class)
    public void addOrder_userIsNotLoggedIn_throwsException() {
        //Arrange
        when(userRepository.getCurrentUser()).thenReturn(null);
        Order order = new Order("new order");
                
        //Act
        Order newOrder = orderController.addOrder(order);
        
        //Assert
        //Throws exception
    }
    
}
