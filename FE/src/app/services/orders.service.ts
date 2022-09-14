import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, retry, throwError } from 'rxjs';
import { Product } from '../models/product.model';
import { Order } from '../models/order.model';

@Injectable()
export class OrderService {
  private baseUrl = 'http://localhost:8080/orders'

  public constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  getOrders(): Observable<Array<Order>> {
    return this.http
      .get<Array<Order>>(
        this.baseUrl,
        this.httpOptions
      )
      .pipe(retry(0), catchError(this.handleError));
  }

  addOrder(order: Order): Observable<boolean> {
    return this.http
      .post<boolean>(
        this.baseUrl,
        JSON.stringify(order),
        this.httpOptions
      )
  }

  handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(() => {
      return errorMessage;
    });
  }
}