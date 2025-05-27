package it.univaq.disim.oop.myunivaq.controller;

public interface DataInitializable<T> {

	/*
	 * Implementato come metodo di default in modo tale che i controllori che
	 * implementano tale interfaccia non sono costretti ad implementare il metodo
	 * qualora non sia necessario
	 */
	default void initializeData(T t) {

	}

}
