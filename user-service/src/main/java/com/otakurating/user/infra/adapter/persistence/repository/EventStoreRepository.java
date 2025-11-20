package com.otakurating.user.infra.adapter.persistence.repository;

import com.otakurating.user.infra.adapter.persistence.entity.StoredEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventStoreRepository extends JpaRepository<StoredEvent, UUID> {

    List<StoredEvent> findByAggregateTypeAndAggregateIdOrderByOccurredAtAsc(String aggregateType, String aggregateId);

    List<StoredEvent> findByEventTypeOrderByOccurredAtDesc(String eventType);

    List<StoredEvent> findAllByOrderByOccurredAtDesc();
}