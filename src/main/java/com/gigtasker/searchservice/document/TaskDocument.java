package com.gigtasker.searchservice.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "tasks")
public class TaskDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "english")
    private String title;

    @Field(type = FieldType.Text, analyzer = "english")
    private String description;

    // "Keyword" type means exact match only (good for filters)
    @Field(type = FieldType.Keyword)
    private String status; // OPEN, ASSIGNED, COMPLETED

    @Field(type = FieldType.Double)
    private BigDecimal minPay;

    @Field(type = FieldType.Double)
    private BigDecimal maxPay;

    @Field(type = FieldType.Date)
    private LocalDate deadline;

    @Field(type = FieldType.Date)
    private LocalDateTime createdAt;

    @GeoPointField
    private GeoPoint location;
}
