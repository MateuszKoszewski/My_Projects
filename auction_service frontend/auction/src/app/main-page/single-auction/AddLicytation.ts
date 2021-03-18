export class AddLicytation {
  private id: number;
  private userEmail: String;
  private price: number;

  constructor(id: number, userEmail: String, price: number) {
    this.id = id;
    this.userEmail = userEmail;
    this.price = price;
  }
}