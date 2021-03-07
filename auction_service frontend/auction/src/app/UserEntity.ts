import { AddressEntity } from './AddressEntity';

export class UserEntity {

  private emailAddress: String;
  private password: String;
  private name: String;
  private lastName: String;
  private address: AddressEntity;

  constructor(emailAddress: String, password: String, name: String, lastName: String, address: AddressEntity) {
    this.emailAddress = emailAddress;
    this.password = password;
    this.name = name;
    this.lastName = lastName;
    this.address = address;
  }

}