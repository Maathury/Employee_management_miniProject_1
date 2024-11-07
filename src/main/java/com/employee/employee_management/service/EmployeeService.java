package com.employee.employee_management.service;

import com.employee.employee_management.dto.EmployeeDto;
import com.employee.employee_management.entity.Employee;
import com.employee.employee_management.exception.ResourceNotFoundException;
import com.employee.employee_management.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;


    public void save(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employeeRepository.save(employee);
    }

    public EmployeeDto getEmpById(Long id) {
//        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
//        Employee employee;
//        if (optionalEmployee.isPresent()) {
//            employee = optionalEmployee.get();
//        } else {
//            throw new RuntimeException("Employee details with provided id " + id + " is not found");
//        }
//        return modelMapper.map(employee, EmployeeDto.class);

        Employee employee = checkIfIdExists(id); //Add custom exceptions : if id is not found, throw Resource Not Found Exception
        return modelMapper.map(employee, EmployeeDto.class);

    }


    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }



//    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
//        EmployeeDto employee=modelMapper.map(employeeDto,EmployeeDto.class);
//        return employeeRepository.save(employee);
//    }

    public Employee updateEmployeeDetails(Employee employeeDto, Long id) {

        Employee employee1=modelMapper.map(employeeDto,Employee.class);
        Employee employee2= checkIfIdExists(id);

        employee2.setFirstName(employee1.getFirstName());
        employee2.setLastName(employee1.getLastName());
        employee2.setEmailId(employee1.getEmailId());
        employeeRepository.save(employee2);
        return modelMapper.map(employee2,Employee.class);
    }

    public void deleteEmpById(Long id) {

        checkIfIdExists(id);
        employeeRepository.deleteById(id);
    }

    private Employee checkIfIdExists(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
    }
}
