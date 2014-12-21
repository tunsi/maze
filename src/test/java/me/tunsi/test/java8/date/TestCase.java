package me.tunsi.test.java8.date;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import org.junit.Test;

public class TestCase {

	/**
	 * Clock、Instant
	 */
	@Test
	public void testCase1() {
		Clock clock = Clock.systemDefaultZone();

		long millis = clock.millis();
		System.out.println(millis);

		Instant instant = clock.instant();
		Date legacyDate = Date.from(instant);

		System.out.println(legacyDate);
	}

	/**
	 * ZoneId
	 */
	@Test
	public void testCase2() {
		System.out.println(ZoneId.getAvailableZoneIds());

		ZoneId zone1 = ZoneId.of("America/Cuiaba");
		System.out.println(zone1.getRules());

		ZoneId zone2 = ZoneId.of("Asia/Shanghai");
		System.out.println(zone2.getRules());

		ZoneId defZone = ZoneId.systemDefault();
		System.out.println(defZone);
		System.out.println(defZone.getRules());
	}

	@Test
	public void testCase3() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("localDateTime: " + localDateTime);

		LocalDate localDate = LocalDate.now();
		System.out.println("localDate: " + localDate);

		LocalTime localTime = LocalTime.now();
		System.out.println("localTime: " + localTime);

		Year year = Year.now();
		System.out.println("获取当前年份: " + year);

		LocalDate localDate2 = year.atDay(59);
		System.out.println("从Year获取LocalDate: " + localDate2);

		LocalDateTime localDateTime2 = localTime.atDate(localDate2);
		System.out.println("把LocalTime关联到一个LocalDate得到一个LocalDateTime: " + localDateTime2);

		Year year1 = Year.of(2012);
		System.out.println("用指定的年获取一个Year: " + year1);

		YearMonth yearMonth = year1.atMonth(2);
		System.out.println("从Year获取YearMonth: " + yearMonth);
		LocalDate localDate3 = yearMonth.atDay(29);
		System.out.println("YearMonth指定日得到 LocalDate: " + localDate3);
		System.out.println("判断是否是闰年: " + localDate3.isLeapYear());

		MonthDay monthDay = MonthDay.of(2, 29);
		LocalDate leapYear = monthDay.atYear(2012);
		System.out.println("自动处理闰年的2月日期: " + leapYear);

		LocalDate nonLeapYear = monthDay.atYear(2011);
		System.out.println("同一个 MonthDay 关联到另一个年份上: " + nonLeapYear);
		
		
		DayOfWeek dayOfWeek = DayOfWeek.of(1);
		System.out.println("dayOfWeek: " + dayOfWeek);
		
		long between =ChronoUnit.DAYS.between(localDate, leapYear);
		System.out.println("计算两个日期之间的天数，还可以按其他时间单位计算两个时间点之间的间隔: " + between);
		
		//线程安全的格式化类，不用每次都new个SimpleDateFormat
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy MM dd");
		System.out.println("把日期时间转换为字符串标识: " + dateTimeFormatter.format(nonLeapYear));
		
		TemporalAccessor temporalAccessor = dateTimeFormatter.parse("2013 01 15");
		System.out.println("解析字符串形式的日期时间: " +  LocalDate.from(temporalAccessor));
		
		Instant instant = Instant.now();
		System.out.println("时间戳: " + instant);
		
		LocalDate with  = nonLeapYear.with(TemporalAdjusters.firstDayOfMonth());
		System.out.println("计算某月的第一天的日期: " + with);
		
		TemporalAdjuster temporalAdjuster = TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY);
		LocalDate with1 = localDate.with(temporalAdjuster);
		System.out.println("计算某月的第一个星期日的日期: " + with1);
		
		LocalDate with2 = localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		System.out.println("计算localDate的下一个星期一的日期: " + with2);
	}
	
	@Test
	public void testCase4() {
		ZoneId id = ZoneId.of("Europe/Paris");
		ZonedDateTime zoned = ZonedDateTime.of(LocalDateTime.now(), id);
		System.out.println(zoned);
		
		ZoneOffset offset = ZoneOffset.of("+2:00");
		ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");
		
		OffsetTime time = OffsetTime.now();
		OffsetTime sameTimeDifferentOffset = time.withOffsetSameInstant(offset);
		OffsetTime changeTimeWithNewOffset = time.withOffsetSameLocal(offset);
		
		System.out.println(sameTimeDifferentOffset);
		changeTimeWithNewOffset.withHour(3).plusSeconds(2);
		
		Period period = Period.of(3, 2, 1);
		LocalDate newDate = LocalDate.now().plus(period);
		System.out.println(newDate);
		
		Duration duration = Duration.ofSeconds(3, 5);
		System.out.println(duration);
//		Duration oneDay = Duration.between(today, yesterday);
	}

}
