import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/register-user.model';
import { BehaviorSubject, catchError, Observable, retry, Subject, throwError } from 'rxjs';
import { Customer } from '../models/login.model';

@Injectable()
export class UserService {
  private baseUrl = 'http://localhost:8080/users'
  public user_id = new BehaviorSubject(0);
  public constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  registerUser(user: User): Observable<User> {
    return this.http
      .post<User>(
        this.baseUrl,
        JSON.stringify(user),
        this.httpOptions
      )
      .pipe(retry(0), catchError(this.handleError));
  }


  LoginUser(customer: Customer): Observable<Customer> {
    return this.http
      .post<Customer>(
        this.baseUrl + '/getrole',
        JSON.stringify(customer),
        this.httpOptions
      )
      .pipe(retry(0), catchError(this.handleError));
  }

  getAll(): Observable<Array<User>> {
    return this.http
      .get<Array<User>>(
        this.baseUrl,
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

  getUserId(): Observable<number> {
    return this.user_id as Observable<number>;
  }
}