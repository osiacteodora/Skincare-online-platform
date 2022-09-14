import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from '../models/order.model';
import { Product } from '../models/product.model';
import { User } from '../models/register-user.model';
import { OrderService } from '../services/orders.service';
import { ProductService } from '../services/products.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-admin-homepage',
  templateUrl: './admin-homepage.component.html',
  styleUrls: ['./admin-homepage.component.scss']
})
export class AdminHomepageComponent implements OnInit {
  public products = Array<Product>();
  public users = Array<User>();
  public orders = Array<Order>();
  public areProductsVisible = true;
  public areUsersVisible = false;
  public areOrdersVisible = false;
  public isAddFormVisible = false;
  public isEditEnabled = Array<boolean>();

  constructor(
    private router: Router,
    private productService: ProductService,
    private userService: UserService,
    private orderService: OrderService,
  ) { }

  ngOnInit(): void {
    this.importProducts();
    this.importOrders();
    this.importUsers();
  }


  importProducts(): void {
    this.productService.getProducts().subscribe((products: Array<Product>) => {
      this.products = products;
      this.isEditEnabled = Array<boolean>();
      this.products.forEach(() => {
        this.isEditEnabled.push(false);
      });
    });
  }

  importUsers(): void {
    this.userService.getAll().subscribe((users) => {
      this.users = users;
    });
  }

  importOrders(): void {
    this.orderService.getOrders().subscribe((orders) => {
      this.orders = orders;
    })
  }

  onAddProductClick(): void {
    this.isAddFormVisible = !this.isAddFormVisible;
  }

  isAddFormDisplayed(): boolean {
    return this.isAddFormVisible;
  }

  addProduct(): void {
    const productToAdd = this.getProductToAdd();

    this.productService.addProduct(productToAdd).subscribe((response) => {
      if (response) {
        alert('Product was added successfully!');
        this.products.push(response);
        this.isEditEnabled.push(false);
      } else {
        alert('Oops, something went wrong, please try again later :(');
      }
    })
  }

  getProductToAdd(): Product {
    const typeInput = document.getElementById('inputType') as HTMLInputElement;
    const skintypeInput = document.getElementById('inputSkinType') as HTMLInputElement;
    const priceInput = document.getElementById('inputPrice') as HTMLInputElement;
    const inputName = document.getElementById('inputName') as HTMLInputElement;
    const brandInput = document.getElementById('inputBrand') as HTMLInputElement;
    const inputStock = document.getElementById('inputStock') as HTMLInputElement;

    const product: Product = {
      id: 0,
      type: typeInput.value,
      skintype: skintypeInput.value,
      price: +priceInput.value,
      name: inputName.value,
      brand: brandInput.value,
      stock: +inputStock.value,
    }

    return product;
  }

  deleteProduct(product: Product): void {
    this.productService.deleteProduct(product.id).subscribe((response) => {
      if (response) {
        alert('Product was deleted successfuly!');
        this.products = this.products.filter((item) => item.id != product.id);
        console.log(this.products);
      }
    })
  }

  onEditClick(index: number): void {
    this.isEditEnabled[index] = !this.isEditEnabled[index];
  }

  editProduct(product: Product, index: number): void {
    const editProduct = this.getEditProduct();
    editProduct.id = product.id;
    if (editProduct.brand == '') {
      editProduct.brand = product.brand;
    }
    if (editProduct.name == '') {
      editProduct.name = product.name;
    }
    if (editProduct.skintype == '') {
      editProduct.skintype = product.skintype;
    }
    if (editProduct.type == '') {
      editProduct.type = product.type;
    }
    if (editProduct.price == 0) {
      editProduct.price = product.price;
    }
    if (editProduct.stock == 0) {
      editProduct.stock = product.stock;
    }
    this.productService.editProduct(editProduct).subscribe((response) => {
      if (response) {
        alert('Product was edited succesfully!');
        this.importProducts();
      }
    })
  }

  getEditProduct(): Product {
    const typeInput = document.getElementById('inputEditType') as HTMLInputElement;
    const skintypeInput = document.getElementById('inputEditSkinType') as HTMLInputElement;
    const priceInput = document.getElementById('inputEditPrice') as HTMLInputElement;
    const inputName = document.getElementById('inputEditName') as HTMLInputElement;
    const brandInput = document.getElementById('inputEditBrand') as HTMLInputElement;
    const inputStock = document.getElementById('inputEditStock') as HTMLInputElement;

    const product: Product = {
      id: 0,
      type: typeInput.value,
      skintype: skintypeInput.value,
      price: +priceInput.value,
      name: inputName.value,
      brand: brandInput.value,
      stock: +inputStock.value,
    }

    return product;
  }

  onProductsClick(): void {
    this.areProductsVisible = true;
    this.areUsersVisible = false;
    this.areOrdersVisible = false;
  }

  onUsersClick(): void {
    this.areOrdersVisible = false;
    this.areProductsVisible = false;
    this.areUsersVisible = true;
  }

  onOrdersClick(): void {
    this.areOrdersVisible = true;
    this.areProductsVisible = false;
    this.areUsersVisible = false;
  }

  getUser(order: Order): string {
    return this.users.filter((user) => user.id_user == order.user_Id)[0].nume;
  }

  getProducts(order: Order): string {
    let result = '';
    order.productIds.forEach(product => {
      result += this.products.filter((prod) => prod.id == product)[0].name + ', ';
    })
    return result;
  }

  search(): void {
    this.importProducts();
    const searchInput = document.getElementById('searchInput') as HTMLInputElement;
    if (searchInput.value == '') {
      this.importProducts();
    }
    this.products = this.products.filter((product) => JSON.stringify(product).includes(searchInput.value));
  }
}
