package edu.umb.cs681.hw12;

public final class Address {
    private final String street, city, state;
    private final int zipcode;

    public Address(String street, String city, String state, int zipcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipcode() {
        return zipcode;
    }
    public boolean equals(Address anotherAddress) {
        if (this.getStreet() == anotherAddress.getStreet()
                && this.getCity() == anotherAddress.getCity()
                && this.getState() == anotherAddress.getState()
                && this.getZipcode() == anotherAddress.getZipcode()) {
            return true;
        }
        else return false;
    }
    public String toString() {
        return street + " " + city + " " + state + " " + zipcode;
    }
    public Address change(String street, String city, String state, int zipcode) {
        return new Address(street, city, state, zipcode);
    }
}
