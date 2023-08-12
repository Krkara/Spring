import { environment } from './../../environment/environment';
import { Person } from 'src/app/models/person.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private url = environment.baseUrl + "/persons";
  constructor(private httpClient: HttpClient) {}

  register(person : Person) {
    return this.httpClient.post(this.url, person);
  }
}
