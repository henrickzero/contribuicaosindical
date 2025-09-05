package com.brcodigo.app.impl.text.mapper;

import com.brcodigo.app.impl.text.enums.TextTypeEnum;
import com.brcodigo.app.impl.text.entity.TextEntity;
import lombok.experimental.UtilityClass;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

@UtilityClass
public class TextMapper {
    private static String TEXT_POINTS = "%s ... ... ... %s ...  Ha ha ha ha ha ha %s";
    private static String TEXT_CHARADE = "%s ... ... ... ... ... ... %s ...  Ha ha ha ha ha ha %s";
    private static String TEXT_EXTRA = " ... ... ... %s";

    public static TextEntity createTextEntityPoints(String question, String answer, String extra){
            return TextEntity.builder()
                    .name(removerAcentosEspeciais(question))
                    .question(question)
                    .answer(answer)
                    .extra(extra)
                    .createDate(LocalDate.now())
                    .full(String.format(TEXT_POINTS,question, answer, Optional.ofNullable(extra)
                            .map(s -> String.format(TEXT_EXTRA, s))
                            .orElse("")))
                    .hasAudio(false)
                    .hasVideo(false)
                    .uploadComplete(false)
                    .textType(TextTypeEnum.POINT)
                    .build();
    }

    public static TextEntity createTextEntityCharades(String question, String answer, String extra){
        return TextEntity.builder()
                .name(removerAcentosEspeciais(question))
                .question(question)
                .answer(answer)
                .extra(extra)
                .createDate(LocalDate.now())
                .full(String.format(TEXT_CHARADE,question, answer, Optional.ofNullable(extra)
                        .map(s -> String.format(TEXT_EXTRA, s))
                        .orElse("")))
                .hasAudio(false)
                .hasVideo(false)
                .uploadComplete(false)
                .textType(TextTypeEnum.CHARADE)
                .build();
    }

    public static String removerAcentosEspeciais(String texto) {
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(textoNormalizado).replaceAll("").replaceAll("[^a-zA-Z0-9 ]", "");
    }

}
