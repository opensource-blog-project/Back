package com.example.opensource_blog.domain.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostImages is a Querydsl query type for PostImages
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostImages extends EntityPathBase<PostImages> {

    private static final long serialVersionUID = 196572863L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostImages postImages = new QPostImages("postImages");

    public final ArrayPath<byte[], Byte> imageData = createArray("imageData", byte[].class);

    public final NumberPath<Long> imageId = createNumber("imageId", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final QPost post;

    public QPostImages(String variable) {
        this(PostImages.class, forVariable(variable), INITS);
    }

    public QPostImages(Path<? extends PostImages> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostImages(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostImages(PathMetadata metadata, PathInits inits) {
        this(PostImages.class, metadata, inits);
    }

    public QPostImages(Class<? extends PostImages> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
    }

}

