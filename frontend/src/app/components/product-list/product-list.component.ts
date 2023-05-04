import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: any;
  currentProduct = null;
  currentIndex = -1;
  name = '';
  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.retrieveProducts();
  }
  retrieveProducts() {
    this.productService.getAll()
      .subscribe(
        data => {
          this.products = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }
  refreshList() {
    this.retrieveProducts();
    this.currentProduct = null;
    this.currentIndex = -1;
  }
  setActiveProduct(product, index) {
    this.currentProduct = product;
    this.currentIndex = index;
  }
  removeAllProducts() {
    this.productService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.refreshList();
        },
        error => {
          console.log(error);
        });
  }
  searchName() {
    this.productService.findByName(this.name)
      .subscribe(
        data => {
          this.products = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }
}
