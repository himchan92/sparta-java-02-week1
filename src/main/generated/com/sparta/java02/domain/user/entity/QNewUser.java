package com.sparta.java02.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNewUser is a Querydsl query type for NewUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNewUser extends EntityPathBase<NewUser> {

    private static final long serialVersionUID = -1547747L;

    public static final QNewUser newUser = new QNewUser("newUser");

    public final StringPath address = createString("address");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final EnumPath<STATUS> status = createEnum("status", STATUS.class);

    public QNewUser(String variable) {
        super(NewUser.class, forVariable(variable));
    }

    public QNewUser(Path<? extends NewUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNewUser(PathMetadata metadata) {
        super(NewUser.class, metadata);
    }

}

