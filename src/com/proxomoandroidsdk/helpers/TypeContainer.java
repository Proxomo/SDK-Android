package com.proxomoandroidsdk.helpers;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeContainer<T> {
	private final Type type;

	protected TypeContainer() {
		ParameterizedType superClass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		System.out.print(superClass);
		type = superClass.getActualTypeArguments()[0];
//		System.out.print(this.getClass().getGenericSuperclass());
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
