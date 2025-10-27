package com.furnistore.furnistore.service;

import com.furnistore.furnistore.model.Customer;
import com.furnistore.furnistore.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository repository;
    private CustomerService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(CustomerRepository.class);
        service = new CustomerService(repository);
    }

    @Test
    void addCustomer_ShouldReturnSavedCustomer() {
        Customer c = new Customer(null, "Juan Torres", "juan@example.com", "3001112233");
        when(repository.save(c)).thenReturn(new Customer(1L, "Juan Torres", "juan@example.com", "3001112233"));

        Customer saved = service.addCustomer(c);

        assertNotNull(saved.getId());
        assertEquals("Juan Torres", saved.getName());
        verify(repository, times(1)).save(c);
    }

    @Test
    void getAllCustomers_ShouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(
                new Customer(1L, "Juan Torres", "juan@example.com", "3001112233")
        ));

        List<Customer> list = service.getAllCustomers();
        assertEquals(1, list.size());
        assertEquals("Juan Torres", list.get(0).getName());
    }

    @Test
    void getAllCustomers_ShouldReturnEmptyList_WhenNoCustomers() {
        when(repository.findAll()).thenReturn(List.of());
        List<Customer> list = service.getAllCustomers();
        assertTrue(list.isEmpty());
    }

    @Test
    void findCustomerById_ShouldReturnCustomer_WhenExists() {
        Customer c = new Customer(1L, "Juan Torres", "juan@example.com", "3001112233");
        when(repository.findById(1L)).thenReturn(Optional.of(c));

        Optional<Customer> found = service.findCustomerById(1L);
        assertTrue(found.isPresent());
        assertEquals("Juan Torres", found.get().getName());
    }

    @Test
    void findCustomerById_ShouldReturnEmpty_WhenNotExists() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<Customer> found = service.findCustomerById(99L);
        assertTrue(found.isEmpty());
    }

    @Test
    void deleteCustomer_ShouldReturnTrueIfExists() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        boolean deleted = service.deleteCustomer(1L);
        assertTrue(deleted);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deleteCustomer_ShouldReturnFalseIfNotExists() {
        when(repository.existsById(2L)).thenReturn(false);

        boolean deleted = service.deleteCustomer(2L);
        assertFalse(deleted);
        verify(repository, never()).deleteById(2L);
    }
}
