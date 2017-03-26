package com.example.todoapp.domain.model.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * LocalDateTime - Timestamp コンバーター
 *
 * 今の時点ではJPAではLocalDateTimeへのコンバータがデフォルトでは用意されていないため独自実装しています。
 * Created by d_akihiro on 2017/03/03.
 */
@Converter(autoApply = true)
public class LocalDateTimeToTimestampConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    /**
     * LocalDateTimeからTimestampへの変換
     * @param localDateTime
     * @return Timestapm
     */
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime != null ? Timestamp.valueOf(localDateTime) : null;
    }

    /**
     * TimestampからLocalDateTimeへの変換
     * @param timestamp
     * @return LocalDateTime
     */
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
