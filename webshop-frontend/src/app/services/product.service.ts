import { environment } from './../../environment/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private url = environment.baseUrl + "/products";

  constructor(private httpClient: HttpClient) {}

  addProduct(product: Product) {
    return this.httpClient.post(this.url, product);
  }

  deleteProduct(product: Product) {
    return this.httpClient.delete<Product[]>(this.url + product.id);
  }

  getProducts() {
    return this.httpClient.get<Product[]>(this.url);
  }

  getProduct(id: number) {
    return this.httpClient.get<Product>(this.url + id);
  }

  editProduct(product: Product) {
    return this.httpClient.put(
      this.url + product.id,
      product
    );
  }

  decreaseStock(product: Product) {
    return this.httpClient.patch(this.url + "/decrease-stock/" + product.id, {});
  }

  increaseStock(product: Product) {
    return this.httpClient.patch(this.url + "/increase-stock/" + product.id, {});

  }
}
