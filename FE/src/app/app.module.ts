import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { UserService } from './services/user.service';
import { SignupComponent } from './signup/signup.component';
import { ClientpageComponent } from './clientpage/clientpage.component';
import { AdminHomepageComponent } from './admin-homepage/admin-homepage.component';
import { RouterModule, Routes } from '@angular/router';
import { ProductService } from './services/products.service';
import { OrderService } from './services/orders.service';

const routes: Routes = [
  {
    path: '', component: LoginComponent
  },
  {
    path: 'client-homepage', component: ClientpageComponent
  },
  {
    path: 'admin-homepage', component: AdminHomepageComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    ClientpageComponent,
    AdminHomepageComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(
      routes,
    )
  ],
  bootstrap: [AppComponent],
  providers: [
    UserService,
    ProductService,
    OrderService,
  ]
})
export class AppModule { }
