package com.example.opensource_blog.domain.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSavePost is a Querydsl query type for SavePost
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSavePost extends EntityPathBase<SavePost> {

    private static final long serialVersionUID = 1867998468L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSavePost savePost = new QSavePost("savePost");

    public final QPost post;

    public final NumberPath<Integer> saveId = createNumber("saveId", Integer.class);

    public final com.example.opensource_blog.domain.users.QUserAccount user;

    public QSavePost(String variable) {
        this(SavePost.class, forVariable(variable), INITS);
    }

    public QSavePost(Path<? extends SavePost> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSavePost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSavePost(PathMetadata metadata, PathInits inits) {
        this(SavePost.class, metadata, inits);
    }

    public QSavePost(Class<? extends SavePost> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
        this.user = inits.isInitialized("user") ? new com.example.opensource_blog.domain.users.QUserAccount(forProperty("user")) : null;
    }

}

