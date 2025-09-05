package com.brcodigo.app.impl.text.repository;

import com.brcodigo.app.impl.text.enums.TextTypeEnum;
import com.brcodigo.app.impl.text.entity.TextEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TextRepository extends MongoRepository<TextEntity, String> {
    List<TextEntity> findByNameAndTextType(String name, TextTypeEnum textType);
    List<TextEntity> findByUploadComplete(Boolean uploadComplete);
    List<TextEntity> findByHasAudio(Boolean hasAudio);
    List<TextEntity> findByHasVideo(Boolean hasVideo);
    List<TextEntity> findByHasVideoAndHasAudio(Boolean hasVideo, Boolean hasAudio);
    List<TextEntity> findByUploadCompleteAndTextType(Boolean uploadComplete, TextTypeEnum textType);
    List<TextEntity> findByHasAudioAndTextType(Boolean hasAudio, TextTypeEnum textType);
    List<TextEntity> findByHasVideoAndTextType(Boolean hasVideo, TextTypeEnum textType);
    List<TextEntity> findByHasVideoAndHasAudioAndTextType(Boolean hasVideo, Boolean hasAudio, TextTypeEnum textType);
}
