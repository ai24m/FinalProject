import { DatePipe } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { HomeComponent } from './components/home/home.component';
import { MarketComponent } from './components/market/market.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProductCommentComponent } from './components/product-id/product-comment/product-comment.component';
import { ProductRatingComponent } from './components/product-id/product-rating/product-rating.component';
import { ProductComponent } from './components/product/product.component';
import { SampleTemplateComponent } from './components/sample-template/sample-template.component';
import { UserComponent } from './components/user/user.component';
import { KeywordPipe } from './pipes/keyword.pipe';
import { AuthService } from './services/auth.service';
import { MarketcommentService } from './services/market-comment.service';
import { MarketRatingService } from './services/market-rating.service';
import { MarketService } from './services/market.service';
import { ProductRatingService } from './services/product-rating.service';
import { ProductService } from './services/product.service';
import { SellerRatingService } from './services/seller-rating.service';
import { UserService } from './services/user.service';
import { SellerRatingComponent } from './components/seller-rating/seller-rating.component';
import { ProductCommentService } from './services/product-comment.service';
import { CdkAccordionModule } from '@angular/cdk/accordion';
import { ContactComponent } from './components/contact/contact.component';
import { SampleTemplate3Component } from './components/sample-template3/sample-template3.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { ProfileComponent } from './components/profile/profile.component';
import { MarketIdComponent } from './components/market-id/market-id.component';
import { MarketCommentComponent } from './components/market-id/market-comment/market-comment.component';
import { MarketRatingComponent } from './components/market-id/market-rating/market-rating.component';
import { ProductIdComponent } from './components/product-id/product-id.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    SampleTemplateComponent,
    CalendarComponent,
    ProductComponent,
    ProductCommentComponent,
    ProductRatingComponent,
    MarketComponent,
    SellerRatingComponent,
    UserComponent,
    KeywordPipe,
    ContactComponent,
    SampleTemplate3Component,
    NotFoundComponent,
    ProfileComponent,
    MarketIdComponent,
    MarketCommentComponent,
    MarketRatingComponent,
    ProductIdComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CdkAccordionModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
  ],
  providers: [
    AuthService,
    UserService,
    SellerRatingService,
    ProductService,
    ProductRatingService,
    ProductCommentService,
    MarketService,
    MarketcommentService,
    MarketRatingService,
    DatePipe,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
