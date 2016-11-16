package com.koodu;

import com.koodu.models.Bookmark;
import com.koodu.models.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import org.springframework.test.context.ActiveProfiles;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class IntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateBookmark() {

        Bookmark bookmark = new Bookmark("af_banjo", "https://google.com", "14-11-2016 09:17", "15-11-2016 09:17");

        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(TestConstants.BASE_URL, bookmark, Response.class);
        Response apiResponse = responseEntity.getBody();
        MediaType mediaType = responseEntity.getHeaders().getContentType();

        assertEquals("Status code mismatch", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Content-Type mismatch", MediaType.APPLICATION_JSON_UTF8, mediaType);
        assertEquals("UserId mismatch", "Success", apiResponse.getMessage());
    }

    @Test
    public void contextLoads() {
    }

}
