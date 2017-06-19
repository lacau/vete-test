package io.redspark.utils;

import io.redspark.utils.mapper.IgnoreLazyCodeGenerationStrategy;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;

public class MapperUtils<E, D> {

    public static MapperFactory factory = createFactory();
    public static MapperFacade mapper = factory.getMapperFacade();

    private Class<E> entityClass;
    private Class<D> dtoClass;

    private static MapperFactory createFactory() {
        return new DefaultMapperFactory.Builder().codeGenerationStrategy(new IgnoreLazyCodeGenerationStrategy()).build();
    }

    public MapperUtils(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    // @Transactional Removed! it will have no effect if not called through a proxy
    // Class should be managed by spring
    //    @Transactional(readOnly = true)
    public D toDTO(E entity) {
        return mapper.map(entity, dtoClass);
    }

    public List<D> toDTO(List<E> entities) {
        return mapper.mapAsList(entities, dtoClass);
    }
}
