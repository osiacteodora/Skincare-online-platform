import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Order } from '../models/order.model';
import { Product } from '../models/product.model';
import { ShoppingCartItem } from '../models/shopping-cart.model';
import { OrderService } from '../services/orders.service';
import { ProductService } from '../services/products.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-clientpage',
  templateUrl: './clientpage.component.html',
  styleUrls: ['./clientpage.component.scss']
})
export class ClientpageComponent implements OnInit {
  public products = Array<Product>();
  public cart = Array<ShoppingCartItem>();
  public isShoppingCartVisible = false;
  private clientId: number = 0;

  constructor(
    private productService: ProductService,
    private userService: UserService,
    private orderService: OrderService,
    private router: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.importProducts();
    this.router.queryParams.subscribe((params) => {
      this.clientId = params['client_id'];
    })
    this.getUserId();
  }

  getUserId(): void {
    this.userService.getUserId().subscribe((userId) => {
      this.clientId = userId;
    })
  }

  importProducts(): void {
    this.productService.getProducts().subscribe((products: Array<Product>) => {
      this.products = products;
    });
  }

  addToCart(product: Product): void {
    let added = false;
    this.cart.forEach((item) => {
      if (item.product.id == product.id) {
        item.quantity++;
        added = true;
      }
    })

    if (added == false) {
      const productToAdd: ShoppingCartItem = {
        product: product,
        quantity: 1
      }
      this.cart.push(productToAdd);
    }
  }

  getAmountOfProductsInCart(): number {
    let sum = 0;
    this.cart.forEach((item) => {
      sum += item.quantity;
    })
    return sum;
  }

  onShoppingCartClick(): void {
    this.isShoppingCartVisible = !this.isShoppingCartVisible;
  }

  checkout(): void {
    if (this.cart.length == 0) return;
    const order = this.prepareOrderBody();

    this.orderService.addOrder(order).subscribe((result) => {
      if (result) {
        alert('Order was placed');
        this.cart = [];
        this.importProducts();
      } else {
        alert('Order wasn\'t placed');
      }
    })
  }

  prepareOrderBody(): Order {
    let productIds: Array<number> = [];
    let productQuantity: Array<number> = [];
    this.cart.forEach((product) => {
      productIds.push(product.product.id);
      productQuantity.push(product.quantity);
    })
    const order: Order = {
      user_Id: this.clientId,
      productIds: productIds,
      quantities: productQuantity
    }

    return order;
  }

  onHomeClick(): void {
    this.isShoppingCartVisible = false;
    this.importProducts();
  }

  search(): void {
    const searchInput = document.getElementById('searchInput') as HTMLInputElement;
    if (searchInput.value == '') {
      this.importProducts();
    }
    this.products = this.products.filter((product) => JSON.stringify(product).includes(searchInput.value));
  }
}
