package com.brcodigo.app.impl.video.mapper;

import com.brcodigo.app.impl.video.entity.VideoEntity;
import com.google.api.services.youtube.model.Video;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VideoMapper {

    public static VideoEntity mapToCreateEntity(Video video) {
        return VideoEntity.builder()
                .videoId(video.getId())
                .videoName(video.getSnippet().getTitle())
                .build();
    }

}
