package com.kaggle.titanic.dbservice.resource;

import com.kaggle.titanic.dbservice.repository.TitanicRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TitanicControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private TitanicController titanicController;
    @MockBean
    private TitanicRepository titanicRepository;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(titanicController)
                .build();
    }

    @Test
    public void testTitanicController() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/titanic")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Welcome"));
    }
    @Test
    public void testTitanicGet() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/titanic/retrieve/{pid}",1)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.passenger").value(1));
    }
}