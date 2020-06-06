package com.connamara;

import java.time.LocalDate;
import java.util.List;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;

import com.opengamma.strata.basics.ReferenceData;
import com.opengamma.strata.basics.StandardId;
import com.opengamma.strata.basics.date.Tenor;
import static com.opengamma.strata.basics.date.BusinessDayConventions.MODIFIED_FOLLOWING;
import com.opengamma.strata.basics.date.BusinessDayAdjustment;
import com.opengamma.strata.basics.date.DaysAdjustment;
import com.opengamma.strata.basics.date.HolidayCalendarIds;
import com.opengamma.strata.basics.date.AdjustableDate;

/**
 * Example to illustrate using the engine to price a Deliverable Swap Future
 * (DSF).
 * <p>
 * This makes use of the example engine and the example market data environment.
 */
public class DateAdjustmentCLI {

    /**
     * Runs the example, pricing the instruments, producing the output as an ASCII
     * table.
     * 
     * @param args ignored
     */
    public static void main(String[] args) {

        // Just grabbing whatever is passed in for now. Not expecting this tools to be an human interfaced client
        for(int i=0;i< args.length;i++)
        {
            System.out.println(args[i]);
        }

        ReferenceData REF_DATA = ReferenceData.standard();
        BusinessDayAdjustment BDA_NONE = BusinessDayAdjustment.NONE;
        BusinessDayAdjustment BDA_FOLLOW_SAT_SUN = BusinessDayAdjustment.of(MODIFIED_FOLLOWING,
                HolidayCalendarIds.SAT_SUN);

        LocalDate baseDate = LocalDate.of(2020, 6, 8);

        AdjustableDate test = AdjustableDate.of(baseDate, BDA_FOLLOW_SAT_SUN);

        System.out.println(test.adjusted(REF_DATA));
    }

    private static final TemporalAdjuster THIRD_WEDNESDAY = TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.WEDNESDAY);

}
