import { RegisterService } from './../../services/register.service';
import { Person } from 'src/app/models/person.model';
import { NgForm } from '@angular/forms';
import { Component } from '@angular/core';
import { ContactData } from 'src/app/models/contact-data.model';
import { Address } from 'src/app/models/address.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  constructor(private registerService : RegisterService) {

  }
  onSubmit(loginForm: NgForm) {
    const formValue = loginForm.value;
  
    const address = new Address(
      formValue.country,
      formValue.county,
      formValue.street,
      formValue.number,
      formValue.postalIndex
    );
  
    const contactData = new ContactData(
      formValue.email,
      formValue.phone,
      address
    );
  
    const newPerson = new Person(
      formValue.personalCode,
      formValue.firstName,
      formValue.lastName,
      formValue.password,
      contactData
    );
  
    this.registerService.register(newPerson).subscribe();
  }
  
}
