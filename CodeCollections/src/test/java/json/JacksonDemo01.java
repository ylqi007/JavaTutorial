package json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.data.Employee;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class JacksonDemo01 {

    /**
     * Serialization converts a Java object into a JSON string.
     */
    @Test
    public void test1() {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Create an Employee object
            Employee employee = new Employee(1, "Alice", "Engineering", "secret123");

            // Convert Java object to JSON (serialization)
            String jsonString = objectMapper.writeValueAsString(employee);
            System.out.println(jsonString);

            // Pretty print JSON
            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);

            System.out.println("Serialized JSON: " + jsonString);
            System.out.println("Pretty JSON:\n" + prettyJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserialization converts a JSON string into a Java object.
     */
    @Test
    public void test2() {
        try {
            // JSON string
            String jsonString = "{ \"emp_id\": 1, \"name\": \"Alice\", \"department\": \"Engineering\" }";

            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert JSON to Java object (deserialization)
            Employee employee = objectMapper.readValue(jsonString, Employee.class);

            // Print Employee object
            System.out.println("Deserialized Employee: " + employee.getName() + " - " + employee.getDepartment());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * For handling arrays or lists of objects:
     */
    @Test
    public void test3() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // List of Employees
            List<Employee> employees = Arrays.asList(
                    new Employee(1, "Alice", "Engineering", "secret123"),
                    new Employee(2, "Bob", "Marketing", "secret456")
            );

            // Serialize List to JSON
            String jsonList = objectMapper.writeValueAsString(employees);
            System.out.println("Serialized List JSON: " + jsonList);

            // Deserialize JSON to List
            List<Employee> deserializedList = objectMapper.readValue(
                    jsonList,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Employee.class)
            );

            System.out.println("Deserialized Employee List: " + deserializedList.get(0).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Using TypeReference
     * Why is this better?
     * 	1.	More Readable & Concise 📝 → Avoids the verbosity of constructCollectionType().
     * 	2.	More Widely Used 🌍 → TypeReference is the preferred approach in most Java projects.
     * 	3.	Better for Generics 🎯 → Works well with complex nested types.
     */
    @Test
    public void test4() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonList = "[{\"emp_id\":1,\"name\":\"Alice\",\"department\":\"Engineering\"},"
                    + "{\"emp_id\":2,\"name\":\"Bob\",\"department\":\"Marketing\"}]";

            // Recommended way to deserialize JSON list
            List<Employee> employees = objectMapper.readValue(jsonList, new TypeReference<List<Employee>>() {});

            // Print results
            employees.forEach(emp -> System.out.println(emp.getName() + " - " + emp.getDepartment()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
