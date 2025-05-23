package fr.diginamic.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.diginamic.app.dto.EmployeeDto;
import fr.diginamic.app.model.Departement;
import fr.diginamic.app.model.Role;
import fr.diginamic.app.service.EmployeeService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc//(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeService employeeService;

    private static Long employeeId;
    EmployeeDto employeeDto = new EmployeeDto();

    @Test
    @WithMockUser(roles = "ADMIN")
    @Order(1)
    void setEmployee() throws Exception {
        employeeDto.setFirstName("Rememba");
        employeeDto.setLastName("Me");
        employeeDto.setEmail("rememba@me.gg");
        employeeDto.setPassword("123456");
        employeeDto.setDepartement(Departement.IT);
        employeeDto.setRole(Role.EMPLOYEE);
        employeeDto.setDaysoffBalance(13.4f);
        employeeDto.setEmplRttBalance(5.2f);

        //Convertir l'objet en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String employeeJson = objectMapper.writeValueAsString(employeeDto);


        MvcResult result = mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(status().isOk())
                .andReturn();

        String responseString = result.getResponse().getContentAsString();
        EmployeeDto createdEmploye = new ObjectMapper().readValue(responseString, EmployeeDto.class);
        employeeId = createdEmploye.getId();

        System.out.println("Created ID: " + employeeId);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @Order(2)
    void testGetEndPoint() throws Exception {
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @Order(3)
    void testGetId() throws Exception {

        mockMvc.perform(get("/api/employees/id/" + employeeId)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(employeeId))
                .andExpect(jsonPath("$.firstName").value("Rememba"))
                .andExpect(jsonPath("$.lastName").value("Me"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @Order(4)
    void testGetEmployeeByRole() throws Exception {
        mockMvc.perform(get("/api/employees/role/EMPLOYEE")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.email == 'rememba@me.gg')]").exists());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @Order(5)
    void testGetEmployeeByDpt() throws Exception {
        mockMvc.perform(get("/api/employees/dpt/IT")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.email == 'rememba@me.gg')]").exists());
    }

    @Test
    @WithMockUser(username = "rememba@me.gg",password = "123456", roles = "ADMIN")
    @Order(6)
    void testGetSolde() throws Exception {

        mockMvc.perform(get("/api/employees/soldeConge")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("13.4"));
    }

    @Test
    @WithMockUser(username = "rememba@me.gg",password = "123456", roles = "ADMIN")
    @Order(7)
    void testGetSoldeRTT() throws Exception {

        mockMvc.perform(get("/api/employees/soldeRTT")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("5.2"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @Order(8)
    void testUpdateEmployee() throws Exception {

        EmployeeDto updatedEmployeeDto = new EmployeeDto();
        updatedEmployeeDto.setFirstName("Rememba");
        updatedEmployeeDto.setLastName("Me");
        updatedEmployeeDto.setEmail("rememba@me.gg");
        updatedEmployeeDto.setPassword("123456");
        updatedEmployeeDto.setDepartement(Departement.IT);
        updatedEmployeeDto.setRole(Role.EMPLOYEE);
        updatedEmployeeDto.setDaysoffBalance(0.4f);
        updatedEmployeeDto.setEmplRttBalance(0.2f);

        //Convertir l'objet en JSON
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/api/employees/id/" + employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEmployeeDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.daysoffBalance").value("0.4"))
                .andExpect(jsonPath("$.emplRttBalance").value("0.2"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @Order(9)
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/employees/id/" + employeeId))
                .andExpect(status().isOk());
    }

}
