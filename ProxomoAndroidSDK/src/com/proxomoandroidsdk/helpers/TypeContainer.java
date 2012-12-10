package com.proxomoandroidsdk.helpers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeContainer<T> {
	private final Type type;
	private volatile Constructor<?> constructor;

	protected TypeContainer() {
		Type superClass = getClass().getGenericSuperclass();
		if (superClass instanceof Class) {
			throw new RuntimeException("Missing type parameter");
		}
		this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
	}

	public T newInstance() throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		if (constructor == null) {
			Class<?> rawType = type instanceof Class<?> ? (Class<?>) type
					: (Class<?>) ((ParameterizedType) type).getRawType();
			constructor = rawType.getConstructor();

		}
		return (T) constructor.newInstance();

	}

	@Override
	public boolean equals(Object o) {
		return o instanceof TypeContainer
				&& ((TypeContainer) o).type.equals(type);
	}

	@Override
	public int hashCode() {
		return type.hashCode();
	}
}
