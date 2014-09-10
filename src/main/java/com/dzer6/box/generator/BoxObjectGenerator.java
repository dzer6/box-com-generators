package com.dzer6.box.generator;

import com.box.boxjavalibv2.dao.BoxObject;
import com.google.common.collect.Maps;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.internal.ParameterContext;
import com.pholser.junit.quickcheck.internal.Reflection;
import com.pholser.junit.quickcheck.internal.ReflectionException;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.lang.reflect.Field;
import java.util.Map;

import static com.pholser.junit.quickcheck.internal.Reflection.allDeclaredFieldsOf;
import static com.pholser.junit.quickcheck.internal.Reflection.findMethod;

/**
 * @author Andrew Panfilov
 */
public abstract class BoxObjectGenerator<T extends BoxObject> extends Generator<T> {

    public static final String FIELD_PREFIX = "FIELD_";
    public static final String GET = "get";
    public static final String IS = "is";
    public static final String UNDERSCORE = "_";
    public static final String EMPTY = "";
    public static final int FIELD_PREFIX_LENGTH = FIELD_PREFIX.length();

    public BoxObjectGenerator(Class<T> type) {
        super(type);
    }

    @Override
    public T generate(final SourceOfRandomness random, final GenerationStatus status) {
        final Class<T> type = types().get(0);

        final Map<String, Object> map = Maps.newHashMap();

        for(final Field field : allDeclaredFieldsOf(type)) {
            final String name = field.getName();

            if (name.contains(FIELD_PREFIX)) {
                final String key = name.substring(FIELD_PREFIX_LENGTH, name.length()).toLowerCase();
                final Object value = generateValue(random, status, type, key);
                map.put(key, value);
            }
        }

        try {
            return Reflection.instantiate(type.getConstructor(Map.class), map);
        } catch (NoSuchMethodException e) {
            e.printStackTrace(); // TODO
            return Reflection.instantiate(type);
        }
    }

    protected Object generateValue(final SourceOfRandomness random,
                                   final GenerationStatus status,
                                   final Class<T> type,
                                   final String key) {
        //System.out.println("generateValue, status: attempts = " + status.attempts() + ", size = " + status.size());
        final Class returnType = getMethodReturnType(type, key);
        return generateValue(random, status, returnType);
    }

    protected <E> E generateValue(final SourceOfRandomness random,
                                  final GenerationStatus status,
                                  final Class<E> type) {
        final ParameterContext parameter = new ParameterContext(type);
        final Generator generator = generatorFor(parameter);
        return (E) generator.generate(random, status);
    }

    protected Class<?> getMethodReturnType(final Class<T> type, final String key) {
        final String camelCase = toCamelCase(key);
        try {
            return findMethod(type, GET + camelCase).getReturnType();
        } catch (ReflectionException exOne) {
            try {
                return findMethod(type, IS + camelCase).getReturnType();
            } catch (ReflectionException exTwo) {
                return findMethod(type, firstCharToLowerCase(camelCase)).getReturnType();
            }
        }
    }

    protected String toCamelCase(final String s){
        final String[] parts = s.split(UNDERSCORE);
        String camelCaseString = EMPTY;
        for (final String part : parts){
            camelCaseString = camelCaseString + toProperCase(part);
        }
        return camelCaseString;
    }

    protected String toProperCase(final String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    protected String firstCharToLowerCase(final String s) {
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }
}