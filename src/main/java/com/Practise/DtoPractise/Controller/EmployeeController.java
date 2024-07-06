package com.Practise.DtoPractise.Controller;

import com.Practise.DtoPractise.DTO.EmployeeDto;
import com.Practise.DtoPractise.Entity.Employee;
import com.Practise.DtoPractise.Repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository erepo;

    @GetMapping("/test")
    public String test(){
        return "This Is A Test Run";
    }

    @PostMapping("/save")
    public String save(@RequestBody EmployeeDto employeeDto)
    {
        Employee emp=new Employee();
        emp.setName(employeeDto.getName());
        emp.setSal(employeeDto.getSal());
        erepo.save(emp);
        return "Data Saved";
    }

    @GetMapping("/all")
    public List<EmployeeDto> findAll(){
        return erepo.findAll().stream().map(employee -> {
            EmployeeDto employeeDto=new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setSal(employee.getSal());
            return employeeDto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable int id){
        Employee e=erepo.findById(id).get();
        EmployeeDto employeeDto=new EmployeeDto();
        BeanUtils.copyProperties(e, employeeDto);
        return employeeDto;
    }

    @GetMapping("/sal/{sal}")
    public List<EmployeeDto> findBySal(@PathVariable long sal){
        return
                erepo.findBySal(sal)
                .stream()
                .map(employee -> {
            EmployeeDto employeeDto=new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setSal(employee.getSal());
            return employeeDto;
        })
                .collect(Collectors.toList());
    }

    @PatchMapping("/upd/{id}")
    public EmployeeDto updateById(@PathVariable int id,@RequestBody EmployeeDto employeeDto){
        Employee e=erepo.findById(id).get();
        e.setName(employeeDto.getName());
        e.setSal(employeeDto.getSal());
        BeanUtils.copyProperties(e, employeeDto);
        erepo.save(e);
        return employeeDto;
    }
}
