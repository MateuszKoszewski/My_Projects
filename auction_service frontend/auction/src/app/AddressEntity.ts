export class AddressEntity {

  private county: String;
  private city: String;
  private street: String;
  private numberOfHouse: number;
  private numberOfFlat: number;
  private postCode: String;

  constructor(county: String, city: String, street: String, numberOfHouse: number, numberOfFlat: number, postCode: String) {

    this.county = county;
    this.city = city;
    this.street = street;
    this.numberOfHouse = numberOfHouse;
    this.numberOfFlat = numberOfFlat;
    this.postCode = postCode;
  }
}