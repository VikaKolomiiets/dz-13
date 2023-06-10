package personalization;
import exceptions.ParentAgeException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Woman extends Person{
    private static final int RETIRED_AGE = 60;
    private final int MIN_YEARS_BETWEEN_WOMAN_BABY = 12;

    public Woman(String firstName, String lastName, LocalDate dateOfBirth) {
        super(firstName, lastName, dateOfBirth);
    }

    public void createFamily(Man newHusband, boolean isChangeLastName, boolean isChangeLastNameNewHusband) {
       createFamilyInner(newHusband, isChangeLastName, isChangeLastNameNewHusband);
    }
    public void giveBabyBirth(Person child)  {
        this.addChild(child);
        if((int) ChronoUnit.YEARS.between(this.getDateOfBirth(), child.getDateOfBirth()) <= MIN_YEARS_BETWEEN_WOMAN_BABY){
            throw new ParentAgeException();
        }
        if (this.getStatus().equals(Status.IS_MARRIED)){
            this.getPartner().addChild(child);
            child.setLastName(this.getPartner().getLastName());
        } else {
            child.setLastName(this.getLastName());
        }
    }
    public void adoptChild(Person child) {
        adoptChildInner(child);
        child.setLastName(this.getPartner().getLastName());
    }
    @Override
    public boolean isRetired() {
        if(getFullAge() >= RETIRED_AGE){
            return true;
        }
        return false;
    }
    @Override
    public String getFullInformation() {
        StringBuilder stringBuilder = new StringBuilder(this.getFirstName() + " " + this.getLastName());
        if (this.getDateOfDeath() == null ){
            stringBuilder.append(this.getFullInformationForAliveWoman());
        } else {
            stringBuilder.append(this.getFullInformationForDiedWoman());
        }
        return stringBuilder.toString();
    }
    private String getFullInformationForAliveWoman(){
        StringBuilder stringBuilder = new StringBuilder(" is " + this.getFullAge() + " years old.");
        switch ( this.getStatus() ){
            case IS_MARRIED -> stringBuilder.append(" She is married with " + this.getPartner().getFirstName() + " " + this.getPartner().getLastName() + ".");
            case SINGLE -> stringBuilder.append(" She is single.");
            case IS_DIVORCED -> stringBuilder.append(" She is divorced.");
            case WIDOWED -> stringBuilder.append(" She is widowed.");
        }
        if(this.getChildren().size() > 0){
            stringBuilder.append(" She has " + this.getChildren().size() + (this.getChildren().size() == 1 ? " child.": " children."));
        }
        if (isRetired()){
            int period = this.getFullAge() - RETIRED_AGE;
            stringBuilder.append(" She has been retired for " + period + (period == 1? " year": " years"));
        }
        return stringBuilder.toString();
    }
    private String getFullInformationForDiedWoman(){
        StringBuilder stringBuilder = new StringBuilder(
                " died in  "
                        + this.getDateOfDeath().getYear()
                        + " at the age of "
                        + this.getFullAge() + " years old.");
        switch ( this.getStatus() ){
            case IS_MARRIED -> stringBuilder.append(" She was married with " + this.getPartner().getFirstName() + " " + this.getPartner().getLastName());
            case SINGLE -> stringBuilder.append(" She was single.");
            case IS_DIVORCED -> stringBuilder.append(" She was divorced.");
            case WIDOWED -> stringBuilder.append(" She was widowed.");
        }
        if(this.getChildren().size() > 0){
            stringBuilder.append(" She had " + this.getChildren().size() + (this.getChildren().size() == 1 ? " child.": " children."));
        }
        return stringBuilder.toString();
    }
}
