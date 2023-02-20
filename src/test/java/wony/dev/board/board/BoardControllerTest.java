package wony.dev.board.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import wony.dev.board.board.model.BoardDto;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@AutoConfigureMockMvc
@SpringBootTest
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("게시글 등록")
    void add() throws Exception {
        // given
        String title = "제목 5";
        String content = "내용 5";
        final BoardDto requestBoardDto = new BoardDto();
        requestBoardDto.setTitle(title);
        requestBoardDto.setContent(content);

        String requestBody = objectMapper.writeValueAsString(requestBoardDto);

        //when
        mockMvc.perform(
                    post("/boards")
                            .content(requestBody)
                            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(
                        document("boards-create"
                                , requestFields(
                                        fieldWithPath("id").type(JsonFieldType.NULL).description("게시판 ID"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("게시판 제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("게시판 내용"),
                                        fieldWithPath("createdAt").type(JsonFieldType.NULL).description("게시판 생성 일자"),
                                        fieldWithPath("updatedAt").type(JsonFieldType.NULL).description("게시판 수정 일자"))
                                , responseFields(
                                        fieldWithPath("id").description("게시판 ID"),
                                        fieldWithPath("title").description("게시판 제목"),
                                        fieldWithPath("content").description("게시판 내용"),
                                        fieldWithPath("createdAt").description("게시판 생성 일자"),
                                        fieldWithPath("updatedAt").description("게시판 수정 일자")
                                )
                        )
                );

    }

    @Test
    @DisplayName("게시글 전부 가져오기")
    void getBoards() throws Exception {
        // given
        // when
        mockMvc.perform(
                    get("/boards")
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("boards-list",
                            responseFields(
                                    fieldWithPath("[].id").description("게시판 ID"),
                                    fieldWithPath("[].title").description("게시판 제목"),
                                    fieldWithPath("[].content").description("게시판 내용"),
                                    fieldWithPath("[].createdAt").description("게시판 생성 일자"),
                                    fieldWithPath("[].updatedAt").description("게시판 수정 일자")
                            )
                        ));

        // then
    }

    @Test
    @DisplayName("게시글 ID로 가져오기")
    void getBoardById() throws Exception {
        // given
        Long id = 101l;

        // when
        mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/boards/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(
                        document("boards-get-id"
                                , pathParameters(
                                        parameterWithName("id").description("게시판 ID"))
                                , responseFields(
                                        fieldWithPath("id").description("게시판 ID"),
                                        fieldWithPath("title").description("게시판 제목"),
                                        fieldWithPath("content").description("게시판 내용"),
                                        fieldWithPath("createdAt").description("게시판 생성 일자"),
                                        fieldWithPath("updatedAt").description("게시판 수정 일자")
                                )
                        )
                );

        // then
    }

    @Test
    @DisplayName("게시글 update")
    void update() throws Exception {
        // given
        Long id = 101l;
        BoardDto boardDto = BoardDto.builder()
                .title("title Update")
                .content("content Update")
                .build();

        // when
        mockMvc.perform(
                    RestDocumentationRequestBuilders.put("/boards/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boardDto)))
                .andExpect(status().isOk())
                .andDo(
                        document("boards-update"
                                , pathParameters(
                                        parameterWithName("id").description("게시판 ID")
                                )
                                , requestFields(
                                        fieldWithPath("id").type(JsonFieldType.NULL).description("게시판 ID"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("게시판 제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("게시판 내용"),
                                        fieldWithPath("createdAt").type(JsonFieldType.NULL).description("게시판 생성 일자"),
                                        fieldWithPath("updatedAt").type(JsonFieldType.NULL).description("게시판 수정 일자")
                                )
                        ));

        // then
    }

    @Test
    @DisplayName("게시글 ID로 삭제")
    void deleteById() throws Exception {
        // given
        Long id = 101l;

        // when
        mockMvc.perform(
                    RestDocumentationRequestBuilders.delete("/boards/{id}", id))
                .andExpect(status().isOk())
                .andDo(
                        document("boards-delete"
                                , pathParameters(
                                        parameterWithName("id").description("게시판 ID"))
                        ));

        // then
    }
}