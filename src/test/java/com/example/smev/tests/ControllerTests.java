package com.example.smev.tests;

import com.example.smev.dto.FineRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.example.smev.utils.Mapper.mapToJson;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private final UUID uuid = UUID.randomUUID();
    @Test
    void RequestTest() throws Exception {
        FineRequest fineRequest = FineRequest.builder().uuid(uuid).taxPayerID("123").build();

        mockMvc.perform(post("/api/v1/fine/request")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(mapToJson(fineRequest))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void ResultTest() throws Exception{
        mockMvc.perform(post("/api/v1/fine/result")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(mapToJson(uuid))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void AcknowledgeTest() throws Exception{
        mockMvc.perform(post("/api/v1/fine/acknowledge")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(mapToJson(uuid))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

}
