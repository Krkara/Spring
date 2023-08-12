import { environment } from './../../environment/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../models/category.model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private url = environment.baseUrl + "/categories";

  constructor(private httpClient: HttpClient) { }

  addCategory() {

  }

  deleteCategory() {

  }

  getCategories() {
    return this.httpClient.get<Category[]>(this.url);
  }

}
