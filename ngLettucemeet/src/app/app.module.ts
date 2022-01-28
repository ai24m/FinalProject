import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from './services/auth.service';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { SampleTemplateComponent } from './components/sample-template/sample-template.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { ProductService } from './services/product.service';
import { ProductComponent } from './components/product/product.component';
import { KeywordPipe } from './pipes/keyword.pipe';
import { ProductCommentComponent } from './components/product-comment/product-comment.component';
import { ProductRatingComponent } from './components/product-rating/product-rating.component';
import { ProductRatingService } from './services/product-rating.service';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    SampleTemplateComponent,
    CalendarComponent,
    ProductComponent,
    KeywordPipe
  ],
  imports: [
    BrowserModule,
    ProductRatingComponent,
    ProductCommentComponent,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    })
  ],
  providers: [AuthService, ProductService, ProductRatingService],
  bootstrap: [AppComponent],
})
export class AppModule { }
