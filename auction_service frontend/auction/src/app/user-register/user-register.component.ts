import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AddressEntity } from '../AddressEntity';
import { UserEntity } from '../UserEntity';
import { UserRegisterService } from './user-register.service';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  constructor(private service: UserRegisterService) { }

  newUser: UserEntity
  userAddress: AddressEntity

  ngOnInit(): void {
  }
  onSubmit(form: NgForm) {
    this.userAddress = new AddressEntity(form.value.county, form.value.city, form.value.street, form.value.homeNumber, form.value.flatNumber, form.value.postCode);
    this.newUser = new UserEntity(form.value.email, form.value.password, form.value.name, form.value.lastName, this.userAddress);
    console.log(this.newUser)
    this.service.registerUser(this.newUser).subscribe(data => console.log(data));
  }

}
