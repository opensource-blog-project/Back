package com.example.opensource_blog.domain.users;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAccount is a Querydsl query type for UserAccount
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAccount extends EntityPathBase<UserAccount> {

    private static final long serialVersionUID = 1791116277L;

    public static final QUserAccount userAccount = new QUserAccount("userAccount");

    public final ListPath<com.example.opensource_blog.domain.comment.Comment, com.example.opensource_blog.domain.comment.QComment> comments = this.<com.example.opensource_blog.domain.comment.Comment, com.example.opensource_blog.domain.comment.QComment>createList("comments", com.example.opensource_blog.domain.comment.Comment.class, com.example.opensource_blog.domain.comment.QComment.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final ListPath<com.example.opensource_blog.domain.post.Post, com.example.opensource_blog.domain.post.QPost> posts = this.<com.example.opensource_blog.domain.post.Post, com.example.opensource_blog.domain.post.QPost>createList("posts", com.example.opensource_blog.domain.post.Post.class, com.example.opensource_blog.domain.post.QPost.class, PathInits.DIRECT2);

    public final StringPath userId = createString("userId");

    public final StringPath username = createString("username");

    public QUserAccount(String variable) {
        super(UserAccount.class, forVariable(variable));
    }

    public QUserAccount(Path<? extends UserAccount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserAccount(PathMetadata metadata) {
        super(UserAccount.class, metadata);
    }

}

