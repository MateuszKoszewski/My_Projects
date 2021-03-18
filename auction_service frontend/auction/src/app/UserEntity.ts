import { AddressEntity } from './AddressEntity';

export class UserEntity {

  emailAddress: string;
  password: String;
  name: String;
  lastName: String;
  address: AddressEntity;

  constructor(emailAddress: string, password: String, name: String, lastName: String, address: AddressEntity) {
    this.emailAddress = emailAddress;
    this.password = password;
    this.name = name;
    this.lastName = lastName;
    this.address = address;
  }

}