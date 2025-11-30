package com.gigtasker.searchservice.listener;

import com.gigtasker.searchservice.document.TaskDocument;
import com.gigtasker.searchservice.dto.TaskDTO;
import com.gigtasker.searchservice.repository.TaskSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TaskEventListener {

    private final TaskSearchRepository searchRepository;

    // Listen to ALL task events (Created, Updated, Completed, Cancelled)
    // We bind to the Topic Exchange: task-exchange
    @RabbitListener(queues = "search.task.events.queue")
    public void handleTaskEvent(TaskDTO taskDto) {
        log.info("♻️ Syncing Task #{} to Elasticsearch...", taskDto.id());

        // Map DTO to Document
        TaskDocument doc = TaskDocument.builder()
                .id(taskDto.id())
                .title(taskDto.title())
                .description(taskDto.description())
                .status(taskDto.status().name())
                .minPay(taskDto.minPay())
                .maxPay(taskDto.maxPay())
                .deadline(taskDto.deadline())
                .build();

        searchRepository.save(doc);
        log.info("✅ Indexed Task #{}", taskDto.id());
    }
}
