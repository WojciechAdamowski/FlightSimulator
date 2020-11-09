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
    public SimpleDate(String date){
        String[] splitDate = date.split("-");
        this.year = checkAndGetYear(splitDate[0]);
        this.month = checkAndGetMonth(splitDate[1]);
        this.day = checkAndGetDay(splitDate[2]);
    }

    /**
     * Return format date
     *
     * @return Date in format "yyyy-MM-dd"
     */
    public String getFormatDateInString(){
        return this.year + "-" + this.month + "-" + this.day;
    }

    private int checkAndGetYear(String year){
        if (year.isEmpty()){
            return checkAndGetMonth(
                    printErrorMessageAndGetNewInput("Given year is empty!")
            );
        } else if (checkIfStringIsNotANumber(year)){
            return checkAndGetMonth(
                    printErrorMessageAndGetNewInput("Given year isn't a number!")
            );
        } else {
            return Integer.parseInt(year);
        }
    }

    private int checkAndGetMonth(String month){
        if (month.isEmpty()){
            return checkAndGetMonth(
                    printErrorMessageAndGetNewInput("Given month is empty!")
            );
        } else if (checkIfStringIsNotANumber(month)){
            return checkAndGetMonth(
                    printErrorMessageAndGetNewInput("Given month isn't a number!")
            );
        } else if (Integer.parseInt(month) <= 1 || Integer.parseInt(month) >= 12){
            return checkAndGetMonth(
                    printErrorMessageAndGetNewInput("This month doesn't exist!")
            );
        }
        else {
            return Integer.parseInt(month);
        }
    }

    private int checkAndGetDay(String day){
        if (day.isEmpty()){
            return checkAndGetMonth(
                    printErrorMessageAndGetNewInput("Given day is empty!")
            );
        } else if (checkIfStringIsNotANumber(day)) {
            return checkAndGetMonth(
                    printErrorMessageAndGetNewInput("Given day isn't a number!")
            );
        } else if (Integer.parseInt(day) <= 1 || Integer.parseInt(day) >= 31){
            return checkAndGetDay(
                    printErrorMessageAndGetNewInput("This day doesn't exist!")
            );
        } else {
            return Integer.parseInt(day);
        }
    }

    private boolean checkIfStringIsNotANumber(String month){
        for (Character character : month.toCharArray()){
            if (!Character.isDigit(character)){
                return true;
            }
        }
        return false;
    }

    private String printErrorMessageAndGetNewInput(String errorMessage){
        System.out.print(errorMessage + " Please write a correct one:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public Date getSimpleDateInDate(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(this.getFormatDateInString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
