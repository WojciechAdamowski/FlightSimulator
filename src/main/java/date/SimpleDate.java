package date;

import date.exceptions.IncorrectDayException;
import date.exceptions.IncorrectMonthException;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Setter
@Getter
public class SimpleDate {
    int year;
    int month;
    int day;

    /**
     * This constructor convert given date in format (yyyy-MM-dd) to year, month, date
     *
     * @param date This parameter is date to convert and must be given in this format(yyyy-MM-dd)
     */
    public SimpleDate(String date ){
        String[] splitDate = date.split("-");
        this.year = Integer.parseInt(splitDate[0]);
        this.month = checkAndGetMonth(Integer.parseInt(splitDate[1]));
        this.day = checkAndGetDay(Integer.parseInt(splitDate[2]));
    }

    /**
     * Return format date
     *
     * @return Date in format "yyyy-MM-dd"
     */
    public String getFormatDate(){
        return this.year + "-" + this.month + "-" + this.day;
    }

    private int checkAndGetMonth(int month){
        if (month >= 1 && month <= 12){
            return month;
        } else {
            return printErrorMessageAndGetNewMonth("This month doesn't exist please choose another one: ");
        }
    }

    private int checkAndGetDay(int day){
        if (day >= 1 && day <= 31){
            return day;
        } else {
            return checkAndGetDay(
                    printErrorMessageAndGetNewDay("This day doesn't exist please choose another one: ")
            );
        }
    }

    private int printErrorMessageAndGetNewMonth(String errorMessage){
        System.out.print(errorMessage);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private int printErrorMessageAndGetNewDay(String errorMessage){
        System.out.print(errorMessage);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public Date getSimpleDateInDate(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(this.getFormatDate());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
