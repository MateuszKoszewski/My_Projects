export class AddressEntity {

  county: String;
  city: String;
  street: String;
  numberOfHouse: number;
  numberOfFlat: number;
  postCode: String;

  constructor(county: String, city: String, street: String, numberOfHouse: number, numberOfFlat: number, postCode: String) {

    this.county = county;
    this.city = city;
    this.street = street;
    this.numberOfHouse = numberOfHouse;
    this.numberOfFlat = numberOfFlat;
    this.postCode = postCode;
  }
}