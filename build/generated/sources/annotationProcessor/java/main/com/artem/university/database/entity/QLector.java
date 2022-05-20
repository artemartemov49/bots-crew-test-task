package com.artem.university.database.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLector is a Querydsl query type for Lector
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLector extends EntityPathBase<Lector> {

    private static final long serialVersionUID = -2076207755L;

    public static final QLector lector = new QLector("lector");

    public final EnumPath<Degree> degree = createEnum("degree", Degree.class);

    public final ListPath<Department, QDepartment> departments = this.<Department, QDepartment>createList("departments", Department.class, QDepartment.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> salary = createNumber("salary", Integer.class);

    public QLector(String variable) {
        super(Lector.class, forVariable(variable));
    }

    public QLector(Path<? extends Lector> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLector(PathMetadata metadata) {
        super(Lector.class, metadata);
    }

}

