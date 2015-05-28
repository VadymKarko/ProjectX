package projectx.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

/**
 * TODO: JavaDoc
 *
 * @author vladimir
 * @since 5/18/15
 */
@Entity
public class Request implements DomainItem {

    @Id
    private ObjectId id;

    private String firstName;
    private String lastName;
    private String illnessDescription;
    private String symptoms;
    private String phoneNumber;
    private Date preferredDate;

    public Request() {
    }

    public Request(String firstName, String lastName, String illnessDescription,
                   String symptoms, String phoneNumber, Date date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.illnessDescription = illnessDescription;
        this.symptoms = symptoms;
        this.phoneNumber = phoneNumber;
        this.preferredDate = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getIllnessDescription() {
        return illnessDescription;
    }

    public void setIllnessDescription(String illnessDescription) {
        this.illnessDescription = illnessDescription;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getPreferredDate() {
        return preferredDate;
    }

    public void setPreferredDate(Date preferredDate) {
        this.preferredDate = preferredDate;
    }

    public ObjectId getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Request{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", illnessDescription='" + illnessDescription + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", preferredDate=" + preferredDate +
                '}'+"\n";
    }
}
