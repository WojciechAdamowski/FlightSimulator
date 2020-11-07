package date;

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
            System.out.print("This month doesn't exist please choose another one: ");
            Scanner scanner = new Scanner(System.in);
            return checkAndGetMonth(scanner.nextInt());
        }
    }

    private int checkAndGetDay(int day){
        if (day >= 1 && day <= 31){
            return day;
        } else {
            System.out.print("This day doesn't exist please choose another one: ");
            Scanner scanner = new Scanner(System.in);
            return checkAndGetDay(scanner.nextInt());
        }
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
