package com.certant.pokedex.handlers;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListHandler {
	
	public static <T> T buscarElemento(List<T> elementos, Predicate<? super T> criterio) {
		return elementos.stream().filter(criterio).findFirst().get();
	}
	
	public static <T> boolean cumpleCondicionElemento(List<T> elementos, Predicate<? super T> criterio) {
		return elementos.stream().anyMatch(criterio);
	}
	
	public static <T> List<T> filtrarElementos(List<T> elementos, Predicate<? super T> criterio) {
		return elementos.stream().filter(criterio).collect(Collectors.toList());
	}
}
