# Mocking

Consider the following case:

A function that checks that a user is logged in, before creating a new order in the database. We only want to test the logic within the method. We would have to use mocking.

    public Order addOrder(Order order) {
        User current = userRepository.getCurrentUser();
        if(current == null) {
            throw new RuntimeException("User is not loged in");
        }
        
        return orderRepository.create(order);
    }

Here we would need to mock out the repository calls to check that the runtime exception is thrown when a user is not logged in. With propper mocking, the tests could look like this.

    @Test
    public void addOrder_userIsLoggedIn_returnsANewOrder() {
        //Arrange
        when(userRepository.getCurrentUser()).thenReturn(new User());
        Order order = new Order("new order");
        when(orderRepository.create(order)).thenReturn(order);
                
        //Act
        Order newOrder = orderController.addOrder(order);
        
        //Assert
        assertEquals(order, newOrder);
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

See the project for how this is implementet.

## Disclaimer

This project is for demonstration purposes only. Throwing a general exception for a spesific exception is not a good idea.
