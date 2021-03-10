export class LocalizationEntity {
  private county: String;
  private city: String;
  private postCode: String;

  constructor(county: String, city: String, postCode: String) {
    this.county = county;
    this.city = city;
    this.postCode = postCode;
  }
}