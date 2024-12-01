package com.example.opensource_blog.domain.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = 84865383L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final ListPath<com.example.opensource_blog.domain.comment.Comment, com.example.opensource_blog.domain.comment.QComment> comments = this.<com.example.opensource_blog.domain.comment.Comment, com.example.opensource_blog.domain.comment.QComment>createList("comments", com.example.opensource_blog.domain.comment.Comment.class, com.example.opensource_blog.domain.comment.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final ListPath<PostImages, QPostImages> images = this.<PostImages, QPostImages>createList("images", PostImages.class, QPostImages.class, PathInits.DIRECT2);

    public final ListPath<com.example.opensource_blog.domain.likes.Like, com.example.opensource_blog.domain.likes.QLike> likes = this.<com.example.opensource_blog.domain.likes.Like, com.example.opensource_blog.domain.likes.QLike>createList("likes", com.example.opensource_blog.domain.likes.Like.class, com.example.opensource_blog.domain.likes.QLike.class, PathInits.DIRECT2);

    public final NumberPath<Integer> postId = createNumber("postId", Integer.class);

    public final StringPath restaurant = createString("restaurant");

    public final StringPath title = createString("title");

    public final com.example.opensource_blog.domain.users.QUserAccount user;

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.opensource_blog.domain.users.QUserAccount(forProperty("user")) : null;
    }

}

