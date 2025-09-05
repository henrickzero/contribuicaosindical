package com.brcodigo.app.impl.text;

import com.brcodigo.app.impl.text.enums.TextTypeEnum;
import com.brcodigo.app.impl.text.entity.TextEntity;
import com.brcodigo.app.impl.text.mapper.TextMapper;
import com.brcodigo.app.impl.text.repository.TextRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class TextService {

    private static final String FOLDER = "C:\\dev\\data\\videos\\%s\\%s.mp4";
    private final TextRepository textRepository;

    public Long lenght(TextTypeEnum textType) {
        if(textType == null){
            return Optional.ofNullable(findByHasAudio(false))
                    .map(textEntities -> textEntities
                            .stream()
                            .mapToLong(value -> value.getFull().length()).sum())
                    .orElse(0L);
        }else{
            return Optional.ofNullable(findByHasAudioAndTextType(false, textType))
                    .map(textEntities -> textEntities
                            .stream()
                            .mapToLong(value -> value.getFull().length()).sum())
                    .orElse(0L);
        }

    }

    @SneakyThrows
    public void checkVideo() {
        List<TextEntity> list = findByHasVideoAndHasAudio(false, true);
        Optional.ofNullable(list)
                .ifPresent(textEntities -> textEntities
                .stream()
                .filter(textEntity -> new File(String.format(FOLDER,
                        textEntity.getTextType().name(),
                        textEntity.getName().replaceAll(" ", "-"))).exists())
                .forEach(textEntity -> {
                    textEntity.setHasVideo(true);
                    save(textEntity);
                    System.out.println("Video encontrado: " +textEntity.getName());
                }));
    }


    @SneakyThrows
    public void loadPontinhos(String text) {
        //String nomeArquivo = "C:\\dev\\data\\texto\\potinhos.txt";
        BufferedReader br = new BufferedReader(new StringReader(text));
        String linha1 = null;
        String linha2 = null;
        String linha3 = null;
        String extra = null;
        while ((linha1 = br.readLine()) != null) {
            linha2 = br.readLine();
            linha3 = br.readLine();
            System.out.println(linha1);
            System.out.println(linha2);
            System.out.println(linha3);
            if(random() == 5){
                extra = "Por favor, inscreva-se no canal e nos ajude a crescer!";
            }
            TextEntity textEntity = TextMapper.createTextEntityPoints(linha1, linha2, extra);
            if(canSave(textEntity.getName(), TextTypeEnum.POINT)){
                save(TextMapper.createTextEntityPoints(linha1, linha2, extra));
            }
        }
        br.close();
    }

    @SneakyThrows
    public void loadCharadas(String text) {
        //String nomeArquivo = "C:\\dev\\data\\texto\\charadas.txt";
        BufferedReader br = new BufferedReader(new StringReader(text));
        String linha1 = null;
        String linha2 = null;
        String linha3 = null;
        String extra = null;
        while ((linha1 = br.readLine()) != null) {
            linha2 = br.readLine();
            linha3 = br.readLine();
            System.out.println(linha1);
            System.out.println(linha2);
            System.out.println(linha3);
            if(random() == 5){
                extra = "Por favor, inscreva-se no canal e nos ajude a crescer!";
            }
            var textEntity =TextMapper.createTextEntityCharades(linha1, linha2, extra);
            if(canSave(textEntity.getName(), TextTypeEnum.CHARADE)) {
                save(TextMapper.createTextEntityCharades(linha1, linha2, extra));
            }
        }
        br.close();
    }

    private int random() {
        Random random = new Random();
        return random.nextInt(7) + 1;
    }

    private boolean canSave(String name, TextTypeEnum textType) {
        return !findByNameAndTextType(name, textType)
                .stream()
                .anyMatch(textEntity -> textEntity.getName()
                        .toLowerCase().equals(name.toLowerCase()));
    }

    public void save(TextEntity textEntity) {
        textRepository.save(textEntity);
    }

    public List<TextEntity> findByUploadComplete(Boolean uploadComplete){
        return textRepository.findByUploadComplete(uploadComplete);
    }
    public List<TextEntity> findByHasAudio(Boolean hasAudio){
        return textRepository.findByHasAudio(hasAudio);
    }
    public List<TextEntity> findByHasVideo(Boolean hasVideo){
        return textRepository.findByHasVideo(hasVideo);
    }
    public List<TextEntity> findByHasVideoAndHasAudio(Boolean hasVideo, Boolean hasAudio){
        return textRepository.findByHasVideoAndHasAudio(hasVideo, hasAudio);
    }
    public List<TextEntity> findByUploadCompleteAndTextType(Boolean uploadComplete, TextTypeEnum textType){
        return textRepository.findByUploadCompleteAndTextType(uploadComplete, textType);
    }
    public List<TextEntity> findByHasAudioAndTextType(Boolean hasAudio, TextTypeEnum textType){
        return textRepository.findByHasAudioAndTextType(hasAudio, textType);
    }
    public List<TextEntity> findByHasVideoAndTextType(Boolean hasVideo, TextTypeEnum textType){
        return textRepository.findByHasVideoAndTextType(hasVideo, textType);
    }
    public List<TextEntity> findByHasVideoAndHasAudioAndTextType(Boolean hasVideo, Boolean hasAudio, TextTypeEnum textType){
        return textRepository.findByHasVideoAndHasAudioAndTextType(hasVideo, hasAudio, textType);
    }
    public List<TextEntity> findByNameAndTextType(String name, TextTypeEnum textType){
        return Optional.ofNullable(textRepository.findByNameAndTextType(name, textType))
                .orElse(new ArrayList<>());
    }

}
