package com.example.challange.services;

import static com.example.challange.utils.Pagination.PageRequest;

import com.example.challange.entities.SwBaseDto;
import com.example.challange.repositories.SwRepository;
import com.example.challange.utils.Json;
import com.example.challange.utils.Pagination;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SwDatasourceService {

	@Autowired
	private SWApiService api;

	//	@Autowired
	//	private SwRepository repo;

	@PostConstruct
	void initDb() {
		try {
			log.info("Database content setting up..");
			//			Set<Map.Entry<String, String>> resourcesSet = api.getResources().entrySet();

			//			for (Map.Entry<String, String> entry : resourcesSet) {
			//				Map<String, Object> content = api.getResourceContent(entry.getValue());
			//				SwBaseDto base = new SwBaseDto(entry.getKey(), Json.toJson(content.get("results")));
			//				var saved = repo.save(base);
			//				log.info("saved: {}", base);
			//			}
		} catch (Exception e) {
			log.error("Service was not able to request or cache the data", e);
			System.exit(1);
		}

		log.info("Database content created!");
	}

	public Pagination findAll(PageRequest pageRequest) {
		return Pagination.buildPagination(pageRequest, null); //.execute(repo);
	}

	public <T> Pagination<T> findAll(PageRequest pageRequest, Class<T> cls) {
		return Pagination.buildPagination(pageRequest, cls);
	}

	public <T> Pagination<T> findResource(String resource) {
		//		Pagination.buildPagination();
		return null;
	}

	public Object findItemResource(String resource, Integer index) {
		return null;
	}

	public Object updateItemResource(String resource, Integer index) {
		return null;
	}
}
