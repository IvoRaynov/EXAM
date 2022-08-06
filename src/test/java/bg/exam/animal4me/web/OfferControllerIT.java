package bg.exam.animal4me.web;


import bg.exam.animal4me.model.entity.OfferEntity;
import bg.exam.animal4me.model.entity.UserEntity;
import bg.exam.animal4me.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;



import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OfferControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestDataUtils testDataUtils;
    private UserEntity testUser,testAdmin;
    private OfferEntity testOffer, testAdminOffer;

    @BeforeEach
    void setUp() {
        testUser = testDataUtils.createTestUser("user@test.com");
        testAdmin = testDataUtils.createTestAdmin("admin@test.com");
        var testModel = testDataUtils.createTestModel(testDataUtils.createTestSpecie());
        testOffer = testDataUtils.createTestOffer(testUser, testModel);
        testAdminOffer = testDataUtils.createTestOffer(testAdmin, testModel);
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }


    @Test
    void testDeleteByAnonymousUser_Forbidden() throws Exception {
        mockMvc.perform(delete("/offers/{id}", testOffer.getId()).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection());

    }

    @Test
    @WithMockUser(username = "admin@test.com", roles = {"ADMIN", "USER"})
    void testDeleteByAdmin() throws Exception {
            mockMvc.perform(delete("/offers/{id}", testOffer.getId())
                            .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/offers/all"));
    }

    @Test
    @WithMockUser(username = "user@test.com", roles = "USER")
    void testDeleteByOwner() throws Exception {
            mockMvc.perform(delete("/offers/{id}", testOffer.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/all"));
    }
    @WithMockUser(username = "user@test.com", roles = "USER")
    @Test
    public void testDeleteNotOwned_Forbidden() throws Exception {
        mockMvc.perform(delete("/offers/{id}", testAdminOffer.getId()).
                        with(csrf())
                ).
                andExpect(status().isForbidden());
    }
}
