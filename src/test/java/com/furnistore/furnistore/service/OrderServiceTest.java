package com.furnistore.furnistore.service;

import com.furnistore.furnistore.model.Customer;
import com.furnistore.furnistore.model.Order;
import com.furnistore.furnistore.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderRepository repository;
    private OrderService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(OrderRepository.class);
        service = new OrderService(repository);
    }

    @Test
    void addOrder_ShouldReturnSavedOrder() {
        Customer c = new Customer(1L, "Juan", "juan@example.com", "3001112233");
        Order o = new Order(null, c);

        when(repository.save(o)).thenReturn(new Order(1L, c));

        Order saved = service.addOrder(o);
        assertNotNull(saved.getId());
        assertEquals("Pending", saved.getStatus());
        verify(repository, times(1)).save(o);
    }

    @Test
    void getAllOrders_ShouldReturnList() {
        Customer c = new Customer(1L, "Juan", "juan@example.com", "3001112233");
        Order o = new Order(1L, c);
        when(repository.findAll()).thenReturn(List.of(o));

        List<Order> list = service.getAllOrders();
        assertEquals(1, list.size());
        assertEquals("Pending", list.get(0).getStatus());
    }

    @Test
    void getAllOrders_ShouldReturnEmptyList_WhenNoOrders() {
        when(repository.findAll()).thenReturn(List.of());
        List<Order> list = service.getAllOrders();
        assertTrue(list.isEmpty());
    }

    @Test
    void findOrderById_ShouldReturnOptional_WhenExists() {
        Customer c = new Customer(1L, "Juan", "juan@example.com", "3001112233");
        Order o = new Order(1L, c);
        when(repository.findById(1L)).thenReturn(Optional.of(o));

        Optional<Order> found = service.findOrderById(1L);
        assertTrue(found.isPresent());
        assertEquals(1L, found.get().getId());
    }

    @Test
    void findOrderById_ShouldReturnEmpty_WhenNotExists() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        Optional<Order> found = service.findOrderById(99L);
        assertTrue(found.isEmpty());
    }

    @Test
    void deleteOrder_ShouldReturnTrueIfExists() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        boolean deleted = service.deleteOrder(1L);
        assertTrue(deleted);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deleteOrder_ShouldReturnFalseIfNotExists() {
        when(repository.existsById(2L)).thenReturn(false);
        boolean deleted = service.deleteOrder(2L);
        assertFalse(deleted);
        verify(repository, never()).deleteById(2L);
    }
}
