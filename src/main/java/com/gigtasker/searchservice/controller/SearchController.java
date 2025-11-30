package com.gigtasker.searchservice.controller;

import com.gigtasker.searchservice.document.TaskDocument;
import com.gigtasker.searchservice.repository.TaskSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final TaskSearchRepository searchRepository;

    @GetMapping("/tasks")
    public ResponseEntity<Page<TaskDocument>> searchTasks(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lon,
            @RequestParam(defaultValue = "10km") String distance, // e.g. "10km", "500m"
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        // 1. Geo Search (Priority)
        if (lat != null && lon != null) {
            return ResponseEntity.ok(
                    searchRepository.findByLocationNear(new GeoPoint(lat, lon), distance, pageable)
            );
        }

        // 2. Text Search
        if (query != null && !query.isBlank()) {
            return ResponseEntity.ok(
                    searchRepository.findByTitleContainingOrDescriptionContaining(query, query, pageable)
            );
        }

        // 3. Fallback (All)
        return ResponseEntity.ok(searchRepository.findAll(pageable));
    }
}
