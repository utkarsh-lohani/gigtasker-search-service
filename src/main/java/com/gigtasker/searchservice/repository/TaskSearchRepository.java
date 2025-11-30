package com.gigtasker.searchservice.repository;

import com.gigtasker.searchservice.document.TaskDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TaskSearchRepository extends ElasticsearchRepository<TaskDocument, Long> {

    // Simple Text Search (Existing)
    Page<TaskDocument> findByTitleContainingOrDescriptionContaining(String title, String description, Pageable pageable);

    // Geo-Spatial Search
    // "Find tasks where 'location' is within 'distance' of 'point'"
    Page<TaskDocument> findByLocationNear(GeoPoint point, String distance, Pageable pageable);
}
