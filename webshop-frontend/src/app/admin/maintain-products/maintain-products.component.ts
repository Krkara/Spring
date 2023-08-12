import { ProductService } from 'src/app/services/product.service';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Product } from 'src/app/models/product.model';

@Component({
  selector: 'app-maintain-products',
  templateUrl: './maintain-products.component.html',
  styleUrls: ['./maintain-products.component.scss']
})
export class MaintainProductsComponent {

  products: Product[] = [];

  constructor(private prodcuctService : ProductService) {} // Dependency Injection

  ngOnInit() { // reserveeritud funktsioon ComponentDidMount
    this.prodcuctService.getProducts().subscribe(res => 
      this.products = res
    );
  }
  
  deleteProduct(product: Product) {
    this.prodcuctService.deleteProduct(product).subscribe(res => 
      this.products = res
    );
  }

  decreaseStock(product: Product) {
    this.prodcuctService.decreaseStock(product);
  }

  increaseStock(product: Product) {
    this.prodcuctService.increaseStock(product);
  }
}
