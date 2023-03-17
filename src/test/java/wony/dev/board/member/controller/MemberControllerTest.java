package wony.dev.board.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import wony.dev.board.member.model.MemberDTO;
import wony.dev.board.member.service.MemberService;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class MemberControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberService memberService;


    @BeforeEach
    void setUp() {
        memberService.deleteAll();
    }


    @Test
    @DisplayName("Member 생성")
    void create() throws Exception {
        // given
        MemberDTO memberDTO = MemberDTO.builder()
                .name("test")
                .build();

        String content = objectMapper.writeValueAsString(memberDTO);

        // expected
        mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andExpect(status().isOk())
                .andDo(
                        document("create-member"
                                , requestFields(
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
                                        , fieldWithPath("id").type(JsonFieldType.NULL).description("ID").optional()
                                        , fieldWithPath("createdAt").type(JsonFieldType.NULL).description("생성일").optional()
                                        , fieldWithPath("updatedAt").type(JsonFieldType.NULL).description("수정일").optional()

                                )
                                , responseFields(
                                        fieldWithPath("id").description("ID")
                                        , fieldWithPath("name").description("이름")
                                        , fieldWithPath("createdAt").description("생성일")
                                        , fieldWithPath("updatedAt").description("수정일")
                                )

                        ));


    }

    @Test
    @DisplayName("Member 전부 찾기")
    void findAll() throws Exception {
        // given
        for (int i = 0; i < 10; i++) {
            MemberDTO memberDTO = MemberDTO.builder()
                    .name("test" + i)
                    .build();

            memberService.save(memberDTO);
        }


        // expected
        mockMvc.perform(
                get("/members")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(
                        document("members",
                                responseFields(
                                        fieldWithPath("[].id").description("ID")
                                        , fieldWithPath("[].name").description("이름")
                                        , fieldWithPath("[].createdAt").description("생성일")
                                        , fieldWithPath("[].updatedAt").description("수정일")
                                )
                        ));
    }

    @Test
    @DisplayName("특정 Member 찾기")
    void findById() throws Exception {
        // given
        MemberDTO memberDTO = MemberDTO.builder()
                .name("test")
                .build();
        MemberDTO saveMemberDTO = memberService.save(memberDTO);
        Long id = saveMemberDTO.getId();

        // expected
        mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/members/{id}", id)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(
                        document("find-member",
                                pathParameters(parameterWithName("id").description("ID")),
                                responseFields(
                                        fieldWithPath("id").description("ID")
                                        , fieldWithPath("name").description("이름")
                                        , fieldWithPath("createdAt").description("생성일")
                                        , fieldWithPath("updatedAt").description("수정일")
                                )
                        )
                );
    }

    @Test
    @DisplayName("Member 수정")
    void update() throws Exception {
        // given
        MemberDTO memberDTO = MemberDTO.builder()
                .name("test")
                .build();
        MemberDTO saveMemberDTO = memberService.save(memberDTO);
        Long id = saveMemberDTO.getId();
        saveMemberDTO.setName("update");

        String content = objectMapper.writeValueAsString(memberDTO);

        // expected
        mockMvc.perform(
                        RestDocumentationRequestBuilders.patch("/members/{id}", id)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andExpect(status().isOk())
                .andDo(
                        document("update-member",
                                pathParameters(parameterWithName("id").description("ID")),
                                requestFields(
                                        fieldWithPath("id").type(JsonFieldType.NULL).description("ID")
                                        , fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
                                        , fieldWithPath("createdAt").type(JsonFieldType.NULL).description("생성일")
                                        , fieldWithPath("updatedAt").type(JsonFieldType.NULL).description("수정일")
                                )
                        )
                );
    }

    @Test
    @DisplayName("Member 삭제")
    void delete() throws Exception {
        // given
        MemberDTO memberDTO = MemberDTO.builder()
                .name("test")
                .build();
        MemberDTO saveMemberDTO = memberService.save(memberDTO);
        Long id = saveMemberDTO.getId();

        // expected
        mockMvc.perform(
                        RestDocumentationRequestBuilders.delete("/members/{id}", id)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(
                        document("delete-member"
                                , pathParameters(
                                        parameterWithName("id").description("게시판 ID"))
                        )
                );
    }

}