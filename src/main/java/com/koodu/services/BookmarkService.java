package com.koodu.services;

/**
 *
 * @author Abiola.Adebanjo
 */
import com.koodu.daos.BookmarkRepository;
import com.koodu.exception.BookmarkException;
import com.koodu.models.Bookmark;
import com.koodu.models.Response;
import com.koodu.utils.Constants;
import com.koodu.utils.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {

    @Autowired
    BookmarkRepository bookmarkRepository;

    public Response createBookmark(Bookmark bookmark) throws BookmarkException {
        if (exists(bookmark)) {
            throw new BookmarkException(Constants.DUPLICATE_ERROR_CODE, Constants.DUPLICATE_ERROR_MESSAGE);
        }
        Bookmark bookmarkResponse = bookmarkRepository.save(bookmark);
        if (bookmarkResponse == null || StringUtils.isEmpty(bookmarkResponse.getId())) {
            throw new BookmarkException(Constants.SERVER_ERROR_CODE, Constants.SERVER_ERROR_MESSAGE);
        } else {
            return new Response(Constants.SUCCESS_MESSAGE);
        }
    }

    public boolean exists(Bookmark bookmark) throws BookmarkException {
        Bookmark bookmarkResponse = bookmarkRepository.findByUserIdAndUrl(bookmark.getUserId(), bookmark.getUrl());
        return bookmarkResponse != null && !StringUtils.isEmpty(bookmarkResponse.getId());
    }

    public Response deleteAllBookmarks() throws BookmarkException {
        bookmarkRepository.deleteAll();
        return new Response(Constants.SUCCESS_MESSAGE);
    }

    public int countBookmarks() throws BookmarkException {
        List<Bookmark> bookmarks = bookmarkRepository.findAll();
        return bookmarks.size();
    }

}
