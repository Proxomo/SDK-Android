package com.proxomoandroidsdk.helpers;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.util.Log;

public class EnumFactory {
	private static HashMap<String, HashMap<Integer, EnumAbstract>> superMap;
	private static EnumFactory factory;

	public static EnumFactory getInstance() {
		if (factory == null) {
			factory = new EnumFactory();
			superMap = new HashMap<String, HashMap<Integer, EnumAbstract>>();
		}
		return factory;
	}

	/**
	 * return a corresponding int value for the enum param if no matches found,
	 * the method return -1
	 * 
	 * @param e
	 * @param enumName
	 * @return
	 */
	public <E extends EnumAbstract> int getIntValue(E e, String enumName) {
		for (Map.Entry<String, HashMap<Integer, EnumAbstract>> entry : superMap
				.entrySet()) {
			if (entry.getKey() == enumName) {
				HashMap<Integer, EnumAbstract> map = entry.getValue();
				for (Map.Entry<Integer, EnumAbstract> subEntry : map.entrySet()) {
					if (subEntry.getValue() == e) {
						return subEntry.getKey();
					}
				}
			}
		}
		return -1;
	}

	/**
	 * return a corresponding enum value for the int param return null if no
	 * matches found
	 * 
	 * @param enumName
	 * @param i
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E extends EnumAbstract> E getEnumValue(String enumName, int i) {
		for (Map.Entry<String, HashMap<Integer, EnumAbstract>> entry : superMap
				.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(enumName)) {
				HashMap<Integer, EnumAbstract> map = entry.getValue();

				for (Map.Entry<Integer, EnumAbstract> subEntry : map.entrySet()) {
					if (subEntry.getKey() == i) {
						return (E) subEntry.getValue();
					}
				}
			}
		}
		return null;
	}

	/**
	 * register an enum type with the factory Whenever the enum changes, remove
	 * the enum and register it again The method checks if the enum is already
	 * been added by itself
	 * 
	 * @param enumClzz
	 */
	@SuppressLint("UseSparseArrays")
	public <E extends EnumAbstract> void registerEnum(Class<E> enumClzz) {
		if (superMap.containsKey(enumClzz.getSimpleName())) {
			return;
		}
		HashMap<Integer, EnumAbstract> map = new HashMap<Integer, EnumAbstract>();
		for (E e : enumClzz.getEnumConstants()) {
			map.put(e.convert(), e);
		}
		superMap.put(enumClzz.getSimpleName(), map);
	}

	/**
	 * use to remove an enum out of the hashmap collection
	 * 
	 * @param enumClzz
	 */
	public <E extends EnumAbstract> void removeEnum(Class<E> enumClzz) {
		if (superMap.containsKey(enumClzz.getSimpleName())) {
			superMap.remove(enumClzz.getSimpleName());
		}
	}
}
