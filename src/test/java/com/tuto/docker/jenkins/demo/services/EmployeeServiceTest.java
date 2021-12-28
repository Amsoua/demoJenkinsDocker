package com.tuto.docker.jenkins.demo.services;

import com.tuto.docker.jenkins.demo.handlers.RecordNotFoundException;
import com.tuto.docker.jenkins.demo.model.Employee;
import com.tuto.docker.jenkins.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class EmployeeServiceTest {

    @InjectMocks
    EmployeeService service;

    @Mock
    EmployeeRepository dao;

    @BeforeEach
    public void init() {

        MockitoAnnotations.openMocks(this);

    }

    @Test
    void whenFindAll_thenOk()
    {
        List<Employee> list = new ArrayList<Employee>();
        Employee empOne = new Employee("John", "John");
        Employee empTwo = new Employee("Alex", "kolenchiski");
        Employee empThree = new Employee("Steve", "Waugh");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(dao.findAll()).thenReturn(list);

        //test
        List<Employee> empList = service.findAll();

        assertEquals(3, empList.size());
        verify(dao, times(1)).findAll();
    }

    @Test
    void givenEmptyList_whenFindAllEmployees_thenValid()
    {
        List<Employee> list = new ArrayList<Employee>();

        when(dao.findAll()).thenReturn(list);

        //test
        List<Employee> empList = service.findAll();

        assertEquals(0, empList.size());

    }

    @Test
    void givenAnEmployee_whenSave_thenOk()
    {
        Employee employee = new Employee("Lokesh","Gupta");

        service.save(employee);

        verify(dao, times(1)).save(employee);
    }

    @Test
    void givenExistingEmployee_whenSaveEmployee_thenOk()
    {

        Employee employee = new Employee(2,"Lokesh","Gupta");
        when(dao.findByFirstName(Mockito.anyString())).thenReturn(Optional.of(employee));
        service.save(employee);
        verify(dao, times(1)).save(employee);

    }

    @Test
    void givenNotExistingId_whenSave_thenThrowException()
    {

        Employee employee = new Employee(2,"Lokesh","Gupta");
        when(dao.findByFirstName(Mockito.anyString())).thenReturn(Optional.empty());

        RecordNotFoundException thrown = assertThrows(
                RecordNotFoundException.class,
                () -> service.save(employee),
                "No employee record exist for given id "+employee.getId()
        );

        assertTrue(thrown.getMessage().contains("No employee"));

    }

    @Test
    void givenExistingEmployee_whenDeleteEmployee_thenOk()
    {

        Employee employee = new Employee(2,"Lokesh","Gupta");
        when(dao.findById(Mockito.any())).thenReturn(Optional.of(employee));
        service.deleteById(employee.getId());
        verify(dao, times(1)).deleteById(employee.getId());

    }

    @Test
    void givenNotExistingId_whenDelete_thenThrowException()
    {

        Employee employee = new Employee(2,"Lokesh","Gupta");
        when(dao.findById(Mockito.any())).thenReturn(Optional.empty());

        RecordNotFoundException thrown = assertThrows(
                RecordNotFoundException.class,
                () -> service.deleteById(employee.getId()),
                "No employee record exist for given id "
        );

        assertTrue(thrown.getMessage().contains("No employee"));

    }

    @Test
    void givenExistingEmployee_whenDeleteAll_thenOk()
    {

        Employee employee = new Employee(2,"Lokesh","Gupta");
        //when(dao.findById(Mockito.any())).thenReturn(Optional.of(employee));
        service.deleteAll();
        verify(dao, times(1)).deleteAll();

    }
}