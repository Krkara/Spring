import { AuthService } from 'src/app/services/auth.service';
import { NgForm } from '@angular/forms';
import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  constructor(private authService : AuthService) {

  }
  
  handleSubmit(signUpForm: NgForm) {
    const formValue = signUpForm.value;

    // validate if passwords match

    const signUpFormData = {
      personalCode: formValue.personalCode,
      firstName: formValue.firstName,
      lastName: formValue.lastName,
      password: formValue.password,
      contactData: {
        email: formValue.email,
        phone: formValue.phone,
        address: {
          country: formValue.country,
          county: formValue.county,
          street: formValue.street,
          number: formValue.number,
          postalIndex: formValue.postalIndex,
        },
      },
    };

    this.authService.register(signUpFormData).subscribe();
  }
  
  fillWithDummyData(signUpForm: NgForm) {
    signUpForm.setValue({
      email: '1@1.com',
      password: '1',
      passwordConfirm: '1',
      firstName: 'John',
      lastName: 'Doe',
      personalCode: '1',
      phone: '+3725554321',
      country: 'Estonia',
      county: 'Harju',
      street: 'Lennujaama tee',
      number: '1',
      postalIndex: '12345',
    });
  }

  clearForm(signUpForm: NgForm) {
    signUpForm.reset();
  }
}

