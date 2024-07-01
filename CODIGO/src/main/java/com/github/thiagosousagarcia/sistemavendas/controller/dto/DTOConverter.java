package com.github.thiagosousagarcia.sistemavendas.controller.dto;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class DTOConverter {
	
	private DTOConverter() {}
	
	public static <S, D> D toObject(final S source, final D destination, final String... ignoreProperties) {
		if (source != null && destination != null) {
			BeanUtils.copyProperties(source, destination, ignoreProperties);
		}
		return destination;
	}

	public static <S, D> D toObject(final S source, final Class<D> destinationType, final String... ignoreProperties) {
		D destination = null;
		if (source != null && destinationType != null) {
			destination = BeanUtils.instantiateClass(destinationType);
			BeanUtils.copyProperties(source, destination, ignoreProperties);
		}
		return destination;
	}

	  
	 public static <D> List<D> toList(final List<?> source, final Class<D> destinationType) {
		List<D> list = null;
		if (source != null && destinationType != null) {
			list = source.stream().map(src -> toObject(src, destinationType)).collect(Collectors.toList());
		}
		return list;
	}

	public static <S, D> Page<D> toPage(final Page<S> sourcePage, final Class<D> destinationType) {
		Page<D> pageReturn = null;
		if (sourcePage != null && destinationType != null) {
			final List<D> list = toList(sourcePage.toList(), destinationType);
			final Pageable pageable = sourcePage.getPageable();
			final long total = sourcePage.getTotalElements();
			pageReturn = new PageImpl<>(list, pageable, total);
		}
		return pageReturn;
	}

	public static <S, D> Page<D> toPage(final Supplier<Page<S>> supplier, final Class<D> destinationType) {
		Page<D> pageReturn = null;
		if (supplier != null && destinationType != null) {
			final Page<S> sourcePage = supplier.get();
			if (sourcePage != null) {
				final List<D> list = toList(sourcePage.toList(), destinationType);
				final Pageable pageable = sourcePage.getPageable();
				final long total = sourcePage.getTotalElements();
				pageReturn = new PageImpl<>(list, pageable, total);
			}
		}
		return pageReturn;
	}
	
}
