public class Customer {
    private String customerId;
    private String customerSSN;
    private String lastName;
    private String firstName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    // Constructor
    public Customer(String customerId, String customerSSN, String lastName, String firstName, String street, String city, String state, String zip, String phone) {
        this.customerId = customerId;
        this.customerSSN = customerSSN;
        this.lastName = lastName;
        this.firstName = firstName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
    }

    // Getters and setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }
    public void setCustomerSSN(String customerSSN) {
        this.customerSSN = customerSSN;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // toString method to return all data for a customer
    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\t' +
                ", customerSSN='" + customerSSN + '\t' +
                ", lastName='" + lastName + '\t' +
                ", firstName='" + firstName + '\t' +
                ", street='" + street + '\t' +
                ", city='" + city + '\t' +
                ", state='" + state + '\t' +
                ", zip='" + zip + '\t' +
                ", phone='" + phone + '\t' +
                '}';
    }
}
