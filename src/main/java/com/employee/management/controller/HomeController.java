package com.employee.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.management.model.Employee;
import com.employee.management.model.UserRole;
import com.employee.management.service.IEmployeeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private IEmployeeService employeeService;
    
    @Autowired
    private HttpServletRequest req;

    @GetMapping("/")
    private String home() {
        return "index";
    }

    @GetMapping("/emp/login")
    private String empLogin() {
        return "emp/login";
    }

    @GetMapping("/hr/login")
    private String hrLogin() {
        return "hr/login";
    }
  
    @GetMapping("/hr/dashboard")
    private String hrDashboard(Model model) {
        Employee e = (Employee) req.getSession().getAttribute("user");
        if (e != null) {
            model.addAttribute("employee", e);
        }
        return "hr/dashboard";
    }

    @GetMapping("/emp/dashboard")
    private String empDashboard(Model model) {
        Employee e = (Employee) req.getSession().getAttribute("user");
        if (e != null) {
            model.addAttribute("employee", e);
        }
        return "emp/dashboard";
    }
    @GetMapping("/emp/calender")
    private String empcalender() {
        return "emp/calender";
    }

    @GetMapping("/hr/calender")
    private String hrcalender() {
        return "hr/calender";
    }
    @PostMapping("/hr/login")
    private String hrDashboard(String email, String password, Model model) {
        Employee e = employeeService.findByEmail(email);
        if (e != null) {
            if (e.getPassword().equals(password) && e.getRole().getName().equals("HR")) {
                req.getSession().setAttribute("user", e);
                return "redirect:/hr/dashboard";
            } else {
                model.addAttribute("error", "Password is incorrect");
                return "hr/login";
            }
        }
        model.addAttribute("error", "User not found");
        return "hr/login";
    }

    @PostMapping("/emp/login")
    private String empLogin(String email, String password, Model model) {
        Employee e = employeeService.findByEmail(email);
        if (e != null) {
            if (e.getPassword().equals(password) && e.getRole().getName().equals("EMPLOYEE")) {
                req.getSession().setAttribute("user", e);
                return "redirect:/emp/dashboard";
            } else {
                model.addAttribute("error", "Password is incorrect");
                return "emp/login";
            }
        }
        model.addAttribute("error", "User not found");
        return "emp/login";
    }
   
    @GetMapping("/logout")
    private String logout() {
        req.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/emp/edit")
    private String empEdit(Model model, String role) {
        Employee e = (Employee) req.getSession().getAttribute("user");
        if (e != null) {
            model.addAttribute("emp", e);
            model.addAttribute("role", e.getRole().getName());
        }
        return "emp/edit_emp";
    }
    @GetMapping("/hr/add")
    private String empadd(Model model, String role) {
        Employee e = (Employee) req.getSession().getAttribute("user");
        if (e != null) {
            model.addAttribute("emp", e);
            model.addAttribute("role", e.getRole().getName());
        }
        return "hr/add_emp";
    }
    @GetMapping("hr/employees")
    private String employeeList(Model model){
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "hr/employees";
    }

    @PostMapping("/emp/edit")
    private String empEdit(
            Long id,
            String firstName,
            String lastName,
            String email,
            String password,
            String contact,
            String qualification,
            String address,
            String birthDate,
            String gender,
            String confirm_password,
            Model model) {
        Employee e = employeeService.getEmployee(id);
        if (password.equals(confirm_password)) {
            String role = e.getRole().getName();
            e.setFirstName(firstName);
            e.setLastName(lastName);
            e.setQualification(qualification);
            e.setContact(contact);
            e.setEmail(email);
            e.setPassword(password);
            e.setAddress(address);
            e.setBirthDate(birthDate);
            e.setGender(gender);
            
            employeeService.saveEmployee(e);
            req.getSession().setAttribute("user", e);
            return role.equals("EMPLOYEE") ? "redirect:/emp/dashboard" : "redirect:/hr/dashboard";
        } else {
            model.addAttribute("error", "Password does not match");
            model.addAttribute("emp", e);
            model.addAttribute("role", e.getRole().getName());
            return "emp/edit_emp";
        }
    }
    @PostMapping("/hr/add")
	public String details(@RequestParam(name = "firstName", required = true) String firstName,
			@RequestParam(name = "lastName", required = true) String lastName,
			@RequestParam(name = "id", required = true) Long id,
			@RequestParam(name = "email", required = true) String email, 
			@RequestParam(name = "password", required = true) String password, 
			@RequestParam(name = "contact", required = true) String contact,
			@RequestParam(name = "qualification", required = true) String qualification,
			@RequestParam(name = "address", required = true) String address,
			@RequestParam(name = "birthDate", required = true) String birthDate,
			@RequestParam(name = "gender", required = true) String gender,
			@RequestParam(name = "role_id", required = true) UserRole role_id,	
			String confirm_password,
			Model model) {

	 Employee employee = new Employee();
	 if (password.equals(confirm_password))
		employee.setId(id);
	    employee.setAddress(address);
		employee.setEmail(email);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setContact(contact);
		employee.setPassword(password);
		employee.setRole(role_id);
		employee.setQualification(qualification);
		employee.setGender(gender);
		employee.setBirthDate(birthDate);
		employeeService.saveEmployee(employee);
		model.addAttribute("employees", employeeService.findAll());
		return "/hr/employees";
	}

}
