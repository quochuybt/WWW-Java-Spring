package iuh.fit.springdatajpa.controller;

import iuh.fit.springdatajpa.dto.EmployeeForm;
import iuh.fit.springdatajpa.dto.EmployeeSearchCriteria;
import iuh.fit.springdatajpa.model.Employee;
import iuh.fit.springdatajpa.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Xem danh sach[cite: 6]
    @GetMapping
    public String list(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employees/list";
    }

    // Tim kiem[cite: 6]
    @GetMapping("/search")
    public String search(@ModelAttribute("criteria") EmployeeSearchCriteria criteria, Model model) {
        if (criteria.hasAny()) {
            model.addAttribute("employees", employeeService.search(criteria));
        }
        return "employees/search";
    }

    // Tao moi[cite: 6]
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("employeeForm", new EmployeeForm());
        return "employees/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("employeeForm") EmployeeForm form,
                         BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "employees/form";
        }
        Employee saved = employeeService.save(null, form);
        redirect.addFlashAttribute("message", "Đã thêm nhân viên " + saved.getName());
        return "redirect:/employees";
    }

    // Chi tiet[cite: 7]
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employees/detail";
    }

    // Cap nhat[cite: 7]
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("employeeForm", employeeService.getForm(id));
        return "employees/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("employeeForm") EmployeeForm form,
                         BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "employees/form";
        }
        Employee saved = employeeService.save(id, form);
        redirect.addFlashAttribute("message", "Đã lưu thay đổi của nhân viên " + saved.getName());
        return "redirect:/employees";
    }

    // Xoa[cite: 7]
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        employeeService.delete(id);
        redirect.addFlashAttribute("message", "Đã xóa nhân viên có ID " + id);
        return "redirect:/employees";
    }
}