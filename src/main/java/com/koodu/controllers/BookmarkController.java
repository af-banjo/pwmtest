package com.koodu.controllers;

import com.koodu.exception.BookmarkException;
import com.koodu.models.Bookmark;
import com.koodu.models.Response;
import com.koodu.services.BookmarkService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abiola.Adebanjo
 */
@RestController
@RequestMapping("/api/v1/bookmark")
public class BookmarkController {

    @Autowired
    BookmarkService bookmarkService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response> createBookmark(@Valid @RequestBody Bookmark bookmark) throws BookmarkException {
        Response response = bookmarkService.createBookmark(bookmark);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
