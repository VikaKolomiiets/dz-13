package personalization;
import exceptions.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Person {
    private static final LocalDate MIN_DATE = LocalDate.of(1900, 01, 01);
    private final String BIRTH_LAST_NAME;
    private final int ADOPTION_PARENT_AGE = 18;
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private Person partner;
    private Status status;
    private List<Person> children;

    public Person(String firstName, String lastName, LocalDate dateOfBirth) {
        this.checkName(firstName);
        this.checkName(lastName);
        this.checkDate(dateOfBirth);
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.BIRTH_LAST_NAME = lastName;
        this.dateOfBirth = dateOfBirth;
        this.partner = null;
        this.status = Status.SINGLE;
        this.dateOfDeath = null;
        children = new ArrayList<>();
    }
    public abstract boolean isRetired();
    public abstract String getFullInformation();
    public int getFullAge(){
        if (this.getDateOfDeath() == null){
            return (int)ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
        } else {
            return (int)ChronoUnit.YEARS.between(dateOfBirth, dateOfDeath);
        }
    }

    public void divorce(boolean isBackLastName, boolean isBackLastNamePartner) {
        this.checkIsAlive(this);;
        this.checkIsAlive(this.getPartner());
        if (this.getStatus() != Status.IS_MARRIED || this.getPartner().getStatus() != Status.IS_MARRIED){
            throw new MerriedStatusException("Check status for both partners", "1:" + this.getFirstName() + " 2:" + this.getPartner().getFirstName());
        }
        this.getPartner().setStatus(Status.IS_DIVORCED);
        this.setStatus(Status.IS_DIVORCED);
        if(isBackLastNamePartner) {
            this.getPartner().backToBirthLastName();
        }
        if(isBackLastName) {
            this.backToBirthLastName();
        }
        this.getPartner().setPartner(null);
        this.setPartner(null);
    }
    public void passAway(LocalDate dateOfDeath) {
        this.checkDate(dateOfDeath);
        if(getDateOfBirth().isAfter(dateOfDeath)){
            throw new OutOfDataRangeException("Date of Death");
        }
        this.setDateOfDeath(dateOfDeath);
        if(this.getStatus() != Status.IS_MARRIED){
            return;
        }
        this.getPartner().setStatus(Status.WIDOWED);
    }

    protected void createFamilyInner(Person newPartner, boolean isChangeLastName, boolean isChangeLastNameNewPartner) {
        if (newPartner == null){
            throw new ObjectNullException("Partner");
        }
        this.checkIsAlive(this);
        this.checkIsAlive(newPartner);
        this.checkMarried(this);
        this.checkMarried(newPartner);
        if (isChangeLastName && isChangeLastNameNewPartner) {
            throw new DoubleActionException();
        }
        this.setStatus(Status.IS_MARRIED);
        newPartner.setStatus(Status.IS_MARRIED);
        this.setPartner(newPartner);
        newPartner.setPartner(this);
        if (isChangeLastName) {
            this.setLastName(newPartner.getLastName());
        }
        if (isChangeLastNameNewPartner){
            newPartner.setLastName(this.getLastName());
        }
    }
    protected void adoptChildInner(Person child) {
        this.checkIsAlive(child);
        if (!this.getStatus().equals(Status.IS_MARRIED)) {
            throw new MerriedStatusException(this.getFirstName(), this.getLastName());
        }
        int personAge = this.getFullAge();
        int partnerAge = this.getPartner().getFullAge();
        if ((this.getFullAge() < ADOPTION_PARENT_AGE) || (this.getPartner().getFullAge() < ADOPTION_PARENT_AGE)){
            throw new ParentAgeException("Both new parent " + this.getFirstName(), this.getPartner().getFirstName());
        }
        this.addChild(child);
        this.getPartner().addChild(child);
    }
    protected void addChild(Person child) {
        this.checkIsAlive(this);
        if (child == null){
            throw new ObjectNullException("Child");
        }
        this.children.add(child);
    }
    private void checkIsAlive(Person person) {
        if(person.getDateOfDeath() != null){
            throw new DeadPersonException(person.getFirstName(), person.getLastName());
        }
    }
    private void checkMarried(Person person) {
        if (person.getStatus().equals(Status.IS_MARRIED)){
            throw new DoubleActionException(person.getFirstName(), person.getLastName());
        }
    }
    private void checkName(String name){
        if (name == null) {
            throw new ObjectNullException("Name");
        }
        if (name.length() < 2) {
            throw new NameException(name);
        }
    }
    private void checkDate(LocalDate date) {
        if (date == null){
            throw new ObjectNullException("Date of Birth");
        }
        if (date.isBefore(MIN_DATE) || date.isAfter(LocalDate.now())){
            throw new OutOfDataRangeException("Date of Birth");
        }
    }
    public void backToBirthLastName() {
        if ((!this.getLastName().equals(this.getBirthLastName()))){
            this.setLastName(this.getBirthLastName());
        }
    }

    //region Getter&Setter
    public UUID getId() {
        return this.id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.checkName(firstName);
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.checkName(lastName);
        this.lastName = lastName;
    }
    public String getBirthLastName() {
        return BIRTH_LAST_NAME;
    }
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }
    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }
    public Person getPartner() {
        return this.partner;
    }
    public Status getStatus() {
        return status;
    }
    public void setPartner(Person partner) {
        if(this.partner == null){
            this.partner = partner;
        }
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setDateOfDeath(LocalDate dateOfDeath) {
        checkDate(dateOfDeath);
        if (!dateOfDeath.isAfter(dateOfBirth)){
            throw new OutOfDataRangeException(dateOfDeath.toString());
        }
        this.dateOfDeath = dateOfDeath;
    }
    public List<Person> getChildren() {
        return children;
    }
    //endregion
}
