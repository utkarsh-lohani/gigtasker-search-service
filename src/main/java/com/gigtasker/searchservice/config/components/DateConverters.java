package com.gigtasker.searchservice.config.components;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DateConverters {

    @ReadingConverter
    public static class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(String source) {
            if (source == null || source.isEmpty()) {
                return null;
            }
            // Try parsing as full date-time
            try {
                return LocalDateTime.parse(source); // ISO-8601 (2023-12-03T10:15:30)
            } catch (Exception e) {
                // Fallback: Parse as Date-only and add start of day
                try {
                    return LocalDate.parse(source).atStartOfDay(); // 2023-12-03 -> 2023-12-03T00:00:00
                } catch (Exception ignored) {
                    return null; // Or throw exception
                }
            }
        }
    }
}
