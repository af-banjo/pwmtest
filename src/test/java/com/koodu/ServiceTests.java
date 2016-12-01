package com.koodu;

import com.koodu.exception.BookmarkException;
import com.koodu.models.Bookmark;
import com.koodu.models.Response;
import com.koodu.services.BookmarkService;
import com.koodu.utils.Constants;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Abiola.Adebanjo
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ServiceTests {

    @Autowired
    BookmarkService bookmarkService;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws BookmarkException {
        bookmarkService.deleteAllBookmarks();
        assertEquals("Respository not cleared", 0, bookmarkService.countBookmarks());
    }

    @Test
    public void testCreateBookmark() throws BookmarkException {
        Bookmark bookmark = new Bookmark("af_banjo_ser", "https://google.com", LocalDateTime.parse("15-11-2016 09:17", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        Response response = bookmarkService.createBookmark(bookmark);

        assertEquals("message mixmatch", "Success", response.getMessage());
    }

    @Test
    public void testCreateBookmarkReturnsDuplicateForExistingBookmark() throws BookmarkException {
        Bookmark bookmark = new Bookmark("af_banjo_1", "https://google.com", LocalDateTime.parse("15-11-2016 09:17", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        Response response = bookmarkService.createBookmark(bookmark);

        assertEquals("message mixmatch", "Success", response.getMessage());

        thrown.expect(BookmarkException.class);
        thrown.expectMessage(Constants.DUPLICATE_ERROR_MESSAGE);
        bookmarkService.createBookmark(bookmark);
    }

}
