package com.example.challange.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T> implements Pageable {

	private int page;
	private int pageSize;
	private Long totalRecords;
	private List<T> data;
	private Sort sort;

	@Override
	public int getPageNumber() {
		return this.page / this.pageSize;
	}

	@Override
	public int getPageSize() {
		return this.pageSize;
	}

	@Override
	public long getOffset() {
		return this.page;
	}

	@Override
	@Nonnull
	public Sort getSort() {
		return this.sort;
	}

	@Override
	@Nonnull
	public Pagination next() {
		setPage(getPage() + getPageSize());
		return this;
	}

	public Pageable previous() {
		if (hasPrevious()) {
			setPage(getPage() - getPageSize());
		}

		return this;
	}

	@Override
	@Nonnull
	public Pageable previousOrFirst() {
		return hasPrevious() ? previous() : first();
	}

	@Override
	@Nonnull
	public Pageable first() {
		setPage(0);
		return this;
	}

	@Override
	@Nonnull
	public Pageable withPage(int pageNumber) {
		setPage(pageNumber);
		return this;
	}

	@Override
	public boolean hasPrevious() {
		return this.page > pageSize;
	}

	public Pagination<T> execute(JpaRepository repo) {
		return execute(repo, null);
	}

	public Pagination<T> execute(JpaRepository repo, Object example) {
		Page<T> page = example != null ? repo.findAll(Example.of(example), this) : repo.findAll(this);

		setTotalRecords(page.getTotalElements());
		setData(page.stream().collect(Collectors.toList()));
		return this;
	}

	private static boolean canOrder(String value) {
		return (
			value != null &&
			!value.isEmpty() &&
			(
				value.equalsIgnoreCase(Sort.Direction.ASC.name()) ||
				value.equalsIgnoreCase(Sort.Direction.DESC.name())
			)
		);
	}

	private static <T> Sort getSort(PageRequest pageRequest, Class<T> cls) {
		String order = pageRequest.getOrder();
		String sort = pageRequest.getSort();
		Sort.Direction direction = canOrder(order)
			? Sort.Direction.fromString(order)
			: Sort.Direction.ASC;

		if (cls == null) {
			return Sort.by(direction);
		}

		List<String> sortableFields = new ArrayList<>();
		if (sort != null && sort.length() > 0) {
			Field[] fields = cls.getFields();
			for (Field f : fields) {
				SerializedName serial = f.getAnnotation(SerializedName.class);
				if (serial != null && serial.value().equalsIgnoreCase(sort)) {
					sortableFields.add(f.getName());
				}
			}
		}

		return Sort.by(direction, sortableFields.toArray(new String[0]));
	}

	public static <T> Pagination buildPagination(PageRequest pageRequest, Class<T> cls) {
		return Pagination
			.builder()
			.page(pageRequest.getPage())
			.pageSize(pageRequest.getPageSize())
			.sort(getSort(pageRequest, cls))
			.build();
	}

	public Map<String, Object> toDTO() {
		Map<String, Object> response = new HashMap<>();
		response.put("page", getPage());
		response.put("pageSize", getPageSize());
		response.put("totalRecords", getTotalRecords());
		response.put("data", getData());
		return response;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PageRequest {

		@Expose
		private int page = 0;

		@Expose
		private int pageSize = 20;

		@Expose
		@Nullable
		private String sort = null;

		@Expose
		private String order = "ASC";
	}
}
