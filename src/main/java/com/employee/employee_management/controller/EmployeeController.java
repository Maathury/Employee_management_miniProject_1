package com.employee.employee_management.controller;

import com.employee.employee_management.dto.EmployeeDto;
import com.employee.employee_management.entity.Employee;
import com.employee.employee_management.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor

public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping("/homePage")
    public String viewHomePage(Model model){

        model.addAttribute("allEmpList", employeeService.getAllEmployees());
        return "index";
    }
    @GetMapping("/addNew")
    public String addNewEmployee(Model model){

        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "newEmployee";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee")EmployeeDto employeeDto){

        employeeService.save(employeeDto);
        return "redirect:/homePage";

    }
    @GetMapping("/showFormForUpdate/{id}")
    public String updateEmployee(@PathVariable(value = "id") Long id, Model model){
        EmployeeDto employee = employeeService.getEmpById(id);
        model.addAttribute("employee", employee);
        return "update";
    }
    @GetMapping("deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {

        employeeService.deleteEmpById(id);
        return "redirect:/homePage";
    }
}
