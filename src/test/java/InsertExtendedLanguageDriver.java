

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import com.google.common.base.CaseFormat;

public class InsertExtendedLanguageDriver extends XMLLanguageDriver implements LanguageDriver {
	private final Pattern inPattern = Pattern.compile("\\(#\\{(\\w+)\\}\\)");

	@Override
	public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {

		Matcher matcher = inPattern.matcher(script);
		if (matcher.find()) {
			StringBuilder sb = new StringBuilder();
			StringBuilder tmp = new StringBuilder();
			sb.append("(");

			for (Field field : parameterType.getDeclaredFields()) {
				if (!"id".equals(field.getName())) {
					sb.append(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName()) + ",");
					tmp.append("#{" + field.getName() + "},");
				}
			}
			sb.deleteCharAt(sb.lastIndexOf(","));
			tmp.deleteCharAt(tmp.lastIndexOf(","));
			sb.append(") values (" + tmp.toString() + ")");
			script = matcher.replaceAll(sb.toString());
			script = "<script>" + script + "</script>";
		}
		return super.createSqlSource(configuration, script, parameterType);
	}
}