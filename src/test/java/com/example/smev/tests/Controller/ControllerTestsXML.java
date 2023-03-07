package com.example.smev.tests.Controller;

import com.example.smev.dto.FineRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.example.smev.utils.Mapper.mapToJson;
import static com.example.smev.utils.Mapper.mapToXML;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ControllerTestsXML {

    @Autowired
    private MockMvc mockMvc;

    private final UUID uuid = UUID.randomUUID();
    @Test
    void RequestTest() throws Exception {
        FineRequest fineRequest = FineRequest.builder().uuid(uuid).taxPayerID("1234567890").build();

        mockMvc.perform(post("/api/v1/fine/request")
                        .contentType(APPLICATION_XML)
                        .content(mapToXML(fineRequest))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void ResultTest() throws Exception{
        mockMvc.perform(get("/api/v1/fine/result/{uuid}",uuid)
                        .contentType(APPLICATION_XML)
                        .accept(TEXT_XML)
                )
                .andDo(print())
                .andExpect(content().contentType("text/xml;charset=UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    void AcknowledgeTest() throws Exception{
        mockMvc.perform(delete("/api/v1/fine/acknowledge/{uuid}",uuid)
                        .contentType(APPLICATION_XML)
                        .accept(TEXT_XML)
                )
                .andDo(print())
                .andExpect(content().contentType("text/xml;charset=UTF-8"))
                .andExpect(status().isOk());
    }

}