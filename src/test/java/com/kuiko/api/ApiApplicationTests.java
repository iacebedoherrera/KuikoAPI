package com.kuiko.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.kuiko.api.model.Community;
import com.kuiko.api.model.CommunityDTO;
import com.kuiko.api.model.Province;
import com.kuiko.api.model.ProvinceDTO;
import com.kuiko.api.service.CommunityService;
import com.kuiko.api.service.ProvinceService;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
class ApiApplicationTests {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProvinceService provinceService;

    @MockBean
    private CommunityService communityService;


    private Province testProvince;
    private Province testProvince2;
	private Community testCommunity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
		testCommunity = new Community();
		testCommunity.setCode("CV");
		testCommunity.setName("COMUNIDAD VALENCIANA");

		testProvince = new Province();
        testProvince.setCode(3);
        testProvince.setName("ALICANTE - ALACANT");
		testProvince.setCommunityCode("CV");
        testProvince.setCommunity(testCommunity);

        testProvince2 = new Province();
        testProvince2.setCode(12);
        testProvince2.setName("CASTELLON/CASTELLO");
		testProvince2.setCommunityCode("CV");
        testProvince2.setCommunity(testCommunity);
    }

    @Test
    public void testProvinceSuccess() throws Exception {
        ProvinceDTO provinceDTO = new ProvinceDTO(testCommunity.getCode(), testCommunity.getName(), 
                                                    testProvince.getCode(), testProvince.getName());
        when(provinceService.getProvinceInfoByProvinceCode(3)).thenReturn(Optional.of(provinceDTO));

        mockMvc.perform(get("/api/province/3")
				.with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.communityCode").value(testProvince.getCommunity().getCode()))
                .andExpect(jsonPath("$.communityName").value(testProvince.getCommunity().getName()))
                .andExpect(jsonPath("$.provinceCode").value(testProvince.getCode()))
                .andExpect(jsonPath("$.provinceName").value(testProvince.getName()));
    }

    @Test
    public void testProvinceNotFound() throws Exception {
        when(provinceService.getProvinceInfoByProvinceCode(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/province/999")
				.with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCommunitySuccess() throws Exception {
        CommunityDTO communityDTO = new CommunityDTO(testCommunity.getCode(), testCommunity.getName(), 2);
        when(communityService.getCommunityInfoByCommunityCode("CV")).thenReturn(Optional.of(communityDTO));

        mockMvc.perform(get("/api/community/CV")
				.with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.communityCode").value(testCommunity.getCode()))
                .andExpect(jsonPath("$.communityName").value(testCommunity.getName()))
                .andExpect(jsonPath("$.provinceCount").value(2));
    }

    @Test
    public void testCommunityNotFound() throws Exception {
        when(provinceService.getProvinceInfoByProvinceCode(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/community/MAD")
				.with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
