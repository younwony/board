package wony.dev.board.member.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberControllerTest {
    @Test
    @DisplayName("Member 전부 찾기")
    void members(){
        // given
        TestRestTemplate restTemplate = new TestRestTemplate();

        // when
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8080/members", List.class, "");

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isGreaterThanOrEqualTo(0);
    }
}