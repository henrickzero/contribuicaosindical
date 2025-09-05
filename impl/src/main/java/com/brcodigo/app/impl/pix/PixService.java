package com.brcodigo.app.impl.pix;

import com.brcodigo.app.impl.pix.model.DadosEnvioPixModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.function.Function;

import static java.lang.Long.toHexString;
import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class PixService {

    private static final String PFI = "01";

    public static final String COD_CRC = "6304";

    private static final String COD_PAIS = "BR";

    private static final String COD_MOEDA = "986";

    private static final String ARRANJO_PAGAMENTO = "BR.GOV.BCB.PIX";

    private static final String MCC = "0000";

    private static final String COD_CAMPO_VALOR = "54";

    private static final String ID_TRANSACAO_VAZIO = "***";

    public byte[] generate(DadosEnvioPixModel dadosEnvioPixModel) {
        return tratarDadosEnvioPix()
                .andThen(saveAndGetBytes())
                .apply(dadosEnvioPixModel);
    }

    public Function<DadosEnvioPixModel, DadosEnvioPixModel> tratarDadosEnvioPix() {
        return dadosEnvioPixModel -> {
            if (requireNonNull(dadosEnvioPixModel.getNomeDestinatario()).isBlank()) {
                throw new IllegalArgumentException("O nome do destinatário é obrigatório.");
            }
            dadosEnvioPixModel.setNomeDestinatario(dadosEnvioPixModel.getNomeDestinatario().trim());

            if (dadosEnvioPixModel.getNomeDestinatario().length() > 25) {
                final var msg = String.format("Nome do destinatário não pode ter mais que 25 caracteres. '%s' tem %d caracteres.",
                        dadosEnvioPixModel.getNomeDestinatario(), dadosEnvioPixModel.getNomeDestinatario().length());
                dadosEnvioPixModel.setNomeDestinatario(dadosEnvioPixModel.getNomeDestinatario().substring(0,25));
            }

            if (requireNonNull(dadosEnvioPixModel.getChaveDestinatario()).isBlank())
                throw new IllegalArgumentException("A chave PIX do destinatário é obrigatória.");
            dadosEnvioPixModel.setChaveDestinatario(dadosEnvioPixModel.getChaveDestinatario().trim());
            if (dadosEnvioPixModel.getChaveDestinatario().length() > 77) {
                final var msg = String.format("Chave PIX do destinatário não pode ter mais que 77 caracteres. '%s' tem %d caracteres.",
                        dadosEnvioPixModel.getChaveDestinatario(), dadosEnvioPixModel.getChaveDestinatario().length());
                dadosEnvioPixModel.setChaveDestinatario(dadosEnvioPixModel.getChaveDestinatario().substring(0,77));
            }

            if (requireNonNull(dadosEnvioPixModel.getCidadeRemetente()).isBlank())
                throw new IllegalArgumentException("A cidade do remetente é obrigatória.");
            dadosEnvioPixModel.setCidadeRemetente(dadosEnvioPixModel.getCidadeRemetente().trim());
            if (dadosEnvioPixModel.getCidadeRemetente().length() > 15) {
                final var msg = String.format("Cidade do remetente não pode ter mais que 15 caracteres. '%s' tem %d caracteres.",
                        dadosEnvioPixModel.getCidadeRemetente(), dadosEnvioPixModel.getCidadeRemetente().length());
                dadosEnvioPixModel.setCidadeRemetente(dadosEnvioPixModel.getCidadeRemetente().substring(0,15));
            }

            requireNonNull(dadosEnvioPixModel.getDescricao(), "A descrição não pode ser nula. Informe um texto vazio no lugar.");
            dadosEnvioPixModel.setDescricao(dadosEnvioPixModel.getDescricao().trim());
            if (dadosEnvioPixModel.getDescricao().length() > 72) {
                final var msg = String.format("Descrição não pode ter mais que 72 caracteres. '%s' tem %d caracteres.",
                        dadosEnvioPixModel.getDescricao(), dadosEnvioPixModel.getDescricao().length());
                dadosEnvioPixModel.setDescricao(dadosEnvioPixModel.getDescricao().substring(0,72));
            }

            if (dadosEnvioPixModel.getValor() == null
                    || dadosEnvioPixModel.getValor().compareTo(BigDecimal.ZERO) <= 0)
                throw new IllegalArgumentException("O valor do PIX deve ser maior que zero.");

            final var valorStr = formatNumber(dadosEnvioPixModel.getValor());
            if (valorStr.length() > 13) {
                final var msg = String.format("Valor não pode ter mais que 13 caracteres. '%s' tem %d caracteres.",
                        valorStr, valorStr.length());
                throw new IllegalArgumentException(msg);
            }
            dadosEnvioPixModel.setValorStr(valorStr);

            if (ObjectUtils.isEmpty(dadosEnvioPixModel.getIdTransacao())) {
                dadosEnvioPixModel.setIdTransacao(ID_TRANSACAO_VAZIO);
            } else {
                dadosEnvioPixModel.setIdTransacao(dadosEnvioPixModel.getIdTransacao().trim());
            }

            if (dadosEnvioPixModel.getIdTransacao().length() > 25) {
                final var msg = String.format("idTransacao deve ter no máximo 25 caracteres. Valor %s tem %d caracteres.",
                        dadosEnvioPixModel.getIdTransacao(), dadosEnvioPixModel.getIdTransacao().length());
                dadosEnvioPixModel.setIdTransacao(dadosEnvioPixModel.getIdTransacao().substring(0,25));
            }
            return dadosEnvioPixModel;
        };
    }

    private String formatNumber(final BigDecimal value) {
        return String.format("%.2f", value).replace(",", ".");
    }

    static Path tempImgFilePath() {
        try {
            return Path.of(File.createTempFile("qrcode-pix-", ".png").getAbsoluteFile().getPath());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private JSONObject newJSONObject(DadosEnvioPixModel dadosPix) {
        ObjectMapper mapper = new ObjectMapper();
        final var jsonTemplate =
                """
                        {
                            '00': '%s',
                            '26': {
                                '00': '%s',
                                '01': '%s',
                                '02': '%s'
                            },
                            '52': '%s',
                            '53': '%s',
                            '%s': '%s',
                            '58': '%s',
                            '59': '%s',
                            '60': '%s',
                            '62': {
                                '05': '%s'
                            }
                        }
                        """;

        final var json =
                jsonTemplate
                        .formatted(
                                PFI, ARRANJO_PAGAMENTO, dadosPix.getChaveDestinatario(), dadosPix.getDescricao(),
                                MCC, COD_MOEDA, COD_CAMPO_VALOR, dadosPix.getValorStr(), COD_PAIS,
                                dadosPix.getNomeDestinatario(), dadosPix.getCidadeRemetente(), dadosPix.getIdTransacao());
        return new JSONObject(json);
    }

    private String generatePix(DadosEnvioPixModel dadosEnvioPixModel) {
        final String partialCode = generateInternal(newJSONObject(dadosEnvioPixModel)) + COD_CRC;
        final String checksum = crcChecksum(partialCode);
        return partialCode + checksum;
    }

    private String generateInternal(final JSONObject jsonObj) {
        final var sb = new StringBuilder();
        jsonObj.keySet().stream().sorted().forEach(key -> {
            final Object val = jsonObj.get(key);
            final String str = encodeValue(key, val);
            sb.append(leftPad(key)).append(strLenLeftPadded(str)).append(str);
        });
        return sb.toString();
    }

    private String encodeValue(final String key, final Object val) {
        //Se o valor para a chave contém outro objeto, processa seus atributos recursivamente
        if (val instanceof JSONObject jsonObjValue)
            return generateInternal(jsonObjValue);

        //Se o valor é String ou um tipo primitivo
        return key.equals(COD_CAMPO_VALOR) ? val.toString() : removeSpecialChars(val);
    }

    private String crcChecksum(final String partialCode) {
        int crc = 0xFFFF;
        final var byteArray = partialCode.getBytes();
        for (final byte b : byteArray) {
            crc ^= b << 8;
            for (int i = 0; i < 8; i++) {
                if ((crc & 0x8000) == 0)
                    crc = crc << 1;
                else crc = (crc << 1) ^ 0x1021;
            }
        }

        final int decimal = crc & 0xFFFF;
        return leftPad(toHexString(decimal), 4).toUpperCase();
    }

    private String removeSpecialChars(final Object value) {
        return value.toString().replaceAll("[^a-zA-Z0-9\\-@\\.\\*\\s]", "");
    }

    static String strLenLeftPadded(final String value) {
        if (value.length() > 99) {
            final var msg = String.format("Tamanho máximo dos valores dos campos deve ser 99. '%s' tem %d caracteres.", value, value.length());
            throw new IllegalArgumentException(msg);
        }

        final String len = String.valueOf(value.length());
        return leftPad(len);
    }

    private static String leftPad(final String code) {
        return leftPad(code, 2);
    }

    private static String leftPad(final String code, final int len) {
        final var format = "%1$" + len + "s";
        return String.format(format, code).replace(' ', '0');
    }

//    private void save(final Path imagePath) {
//        saveAndGetBytes(imagePath);
//    }
//
//    public Path save() {
//        final Path imagePath = tempImgFilePath();
//        saveAndGetBytes(imagePath);
//        return imagePath;
//    }

    public Function<DadosEnvioPixModel, byte[]> saveAndGetBytes() {
        //Obtém a extensão do arquivo
//        final var fileFormat = FilenameUtils.getExtension(imagePath.toString());
//        if (fileFormat.isEmpty())
//            throw new IllegalArgumentException("Nome do arquivo deve conter a extensão para indicar o formato da imagem");
        return dadosEnvioPixModel -> {

            final var hintsMap = new EnumMap<>(EncodeHintType.class);
            hintsMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hintsMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            final int tamanho = 300; // Tamanho da imagem do QRCode em pixels

            final var writer = new QRCodeWriter();
            try {
                final var bitMatrix = writer.encode(generatePix(dadosEnvioPixModel), BarcodeFormat.QR_CODE, tamanho, tamanho, hintsMap);
                final var image = new BufferedImage(tamanho, tamanho, BufferedImage.TYPE_INT_RGB);
                for (int y = 0; y < tamanho; y++) {
                    for (int x = 0; x < tamanho; x++) {
                        final var isBlack = bitMatrix.get(x, y);
                        final int color = isBlack ? 0 : 0xFFFFFF; //black or white
                        image.setRGB(x, y, color);
                    }
                }

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "png", baos);
                return baos.toByteArray();
            } catch (IOException | WriterException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
