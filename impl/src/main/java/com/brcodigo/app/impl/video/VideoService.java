package com.brcodigo.app.impl.video;

import com.brcodigo.app.impl.video.entity.VideoEntity;
import com.brcodigo.app.impl.video.mapper.VideoMapper;
import com.brcodigo.app.impl.video.repository.VideoRepository;
import com.google.api.services.youtube.model.Video;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoEntity create(Video video) {

        return videoRepository.save(VideoMapper.mapToCreateEntity(video));
    }

    public VideoEntity findByVideoId(String videoId) {
        return videoRepository.findByVideoId(videoId);
    }
}
