import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, retry, throwError } from 'rxjs';
import { Product } from '../models/product.model';

@Injectable()
export class ProductService {
  private baseUrl = 'http://localhost:8080/products'

  public constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  addProduct(product: Product): Observable<Product> {
    return this.http
      .post<Product>(
        this.baseUrl,
        JSON.stringify(product),
        this.httpOptions
      )
      .pipe(retry(0), catchError(this.handleError));
  }


  getProducts(): Observable<Array<Product>> {
    return this.http
      .get<Array<Product>>(
        this.baseUrl,
        this.httpOptions
      )
      .pipe(retry(0), catchError(this.handleError));
  }

  deleteProduct(id: number): Observable<boolean> {
    return this.http
      .delete<boolean>(
        this.baseUrl + '/' + id,
        this.httpOptions
      ).pipe(retry(0), catchError(this.handleError));
  }

  editProduct(product: Product): Observable<Product> {
    return this.http
      .put<Product>(
        this.baseUrl + '/' + product.id,
        product,
        this.httpOptions
      )
      .pipe(retry(0), catchError(this.handleError));
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