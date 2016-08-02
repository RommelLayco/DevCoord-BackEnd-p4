package nz.ac.auckland.devcoord.database;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Code to store type LocalDate into database
 * 
 * taken from http://www.java2s.com/Tutorials/Java/Data_Type_How_to/Date_Convert/Convert_LocalDate_to_java_util_Date.htm
 * and : http://www.java2s.com/Tutorials/Java/Data_Type_How_to/Date_Convert/Convert_java_util_Date_to_LocalDate.htm
 *
 * 
 *
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate date) {
		if (date == null) {
			return null;
		}
		
		Instant instant = date.atStartOfDay().atZone(ZoneId.systemDefault())
				.toInstant();
		return Date.from(instant);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date value) {
		if (value == null) {
			return null;
		}

		Instant instant = Instant.ofEpochMilli(value.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
				.toLocalDate();
	}

}
