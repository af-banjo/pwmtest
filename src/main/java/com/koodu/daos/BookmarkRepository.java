package com.koodu.daos;

import com.koodu.models.Bookmark;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Abiola.Adebanjo
 */
public interface BookmarkRepository extends MongoRepository<Bookmark, String> {

    @Override
    public Bookmark save(Bookmark bookmark);

    public Bookmark findByUserIdAndUrl(String userId, String url);
}
