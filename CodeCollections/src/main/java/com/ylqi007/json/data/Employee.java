package com.ylqi007.json.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
    private int id;
    private String name;
    private String department;

    @JsonIgnore // This field will be ignored in JSON serialization
    private String password;    // password field is ignored due to @JsonIgnore.

    public Employee() {} // Default constructor needed for deserialization

    public Employee(int id, String name, String department, String password) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.password = password;
    }

    @JsonProperty("emp_id") // Custom JSON field name
    public int getId() { return id; }   // id is renamed as "emp_id" using @JsonProperty.

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }

    public void setDepartment(String department) { this.department = department; }
}