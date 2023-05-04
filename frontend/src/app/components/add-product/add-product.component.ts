import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  product = {
    id:'',
    name: '',
    price: '',
    description: ''
  };
  submitted = false;
  constructor(private productService:ProductService) { }

  ngOnInit(): void {
  }
  saveProduct() {
    const data = {
      id: this.product.id,
      name: this.product.name,
      description: this.product.description,
      price: this.product.price
    };

    this.productService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }
  newProduct() {
    this.submitted = false;
    this.product = {
      id:'',
      name: '',
      price: '',
      description: ''
    };
  }
}
