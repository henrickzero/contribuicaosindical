package com.brcodigo.app.impl.video.repository;

import com.brcodigo.app.impl.video.entity.VideoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<VideoEntity, String> {
    VideoEntity findByVideoId(String videoId);
}
