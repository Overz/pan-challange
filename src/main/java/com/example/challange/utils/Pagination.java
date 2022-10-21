package com.example.challange.utils;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagination implements Pageable, Serializable {
	private int page;
	private int pageSize;
	private Long totalRecords;
	private Object data;
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
	public Sort getSort() {
		return this.sort;
	}

	@Override
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
	public Pageable previousOrFirst() {
		return hasPrevious() ? previous() : first();
	}

	@Override
	public Pageable first() {
		setPage(0);
		return this;
	}

	@Override
	public Pageable withPage(int pageNumber) {
		setPage(pageNumber);
		return this;
	}

	@Override
	public boolean hasPrevious() {
		return this.page > pageSize;
	}

	public Pagination execute(JpaRepository repo) {
		Object data = getData();

		Page<?> page = data != null ?
			repo.findAll(Example.of(data), this) :
			repo.findAll(this);

		setTotalRecords(page.getTotalElements());
		setData(page.stream().collect(Collectors.toList()));

		return this;
	}

	public static Pagination buildPagination(int page, int pageSize, String sort, Object o) {
		String[] content = sort.split(",");
		Sort.Direction direction = Sort.Direction.fromString(content[1]);
		Pagination.PaginationBuilder builder = Pagination.builder();

		Field[] fields = o.getClass().getDeclaredFields();
		for (Field field : fields) {
			SerializedName serial = field.getAnnotation(SerializedName.class);
			if (serial != null && serial.value().equalsIgnoreCase(content[0])) {
				builder.sort(Sort.by(direction, serial.alternate()[0]));
				break;
			}
		}

		return builder.build();
	}

	private static boolean isSortable(String value, Sort.Direction direction) {
		return value.equalsIgnoreCase(direction.name());
	}

	public static <T> Pagination buildPagination(int page, int pageSize, String sort, Class<T> cls) {
		PaginationBuilder builder = Pagination.builder().page(page).pageSize(pageSize);

		// name,asc
		if (sort != null && sort.length() > 0) {
			Field[] fields = cls.getFields();
			String[] content = sort.split(",");
			if (content.length > 0) {
				Sort.Direction direction = Sort.Direction.ASC;

				if (content.length >= 2 && isSortable(content[1], Sort.Direction.ASC) || isSortable(content[1], Sort.Direction.DESC)) {
					direction = Sort.Direction.fromString(content[1]);

					for (Field f : fields) {
						SerializedName serial = f.getAnnotation(SerializedName.class);
						if (serial.value().equalsIgnoreCase(content[0])) {
							builder.sort(Sort.by(direction, f.getName()));
						}
					}
				} else {
					builder.sort(Sort.by(direction));
				}
			}
		}

		return builder.build();
	}

	public Object toDTO() {
		Map<String, Object> response = new HashMap<>();
		response.put("page", getPage());
		response.put("pageSize", getPageSize());
		response.put("totalRecords", getTotalRecords());
		response.put("data", getData());
		return response;
	}
}
