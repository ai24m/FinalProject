import { DatePipe } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { BrowserModule } from "@angular/platform-browser";
import { CalendarModule, DateAdapter } from "angular-calendar";
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { CalendarComponent } from "./components/calendar/calendar.component";
import { HomeComponent } from "./components/home/home.component";
import { MarketComponent } from "./components/market/market.component";
import { NavbarComponent } from "./components/navbar/navbar.component";
import { ProductCommentComponent } from "./components/product-comment/product-comment.component";
import { ProductRatingComponent } from "./components/product-rating/product-rating.component";
import { ProductComponent } from "./components/product/product.component";
import { SampleTemplateComponent } from "./components/sample-template/sample-template.component";
import { UserComponent } from "./components/user/user.component";
import { KeywordPipe } from "./pipes/keyword.pipe";
import { AuthService } from "./services/auth.service";
import { MarketcommentService } from "./services/market-comment.service";
import { MarketRatingService } from "./services/market-rating.service";
import { MarketService } from "./services/market.service";
import { ProductRatingService } from "./services/product-rating.service";
import { ProductService } from "./services/product.service";
import { SellerRatingService } from "./services/seller-rating.service";
import { UserService } from "./services/user.service";
import { SellerRatingComponent } from './components/seller-rating/seller-rating.component';
import { ProductCommentService } from "./services/product-comment.service";
import { CdkAccordionModule} from '@angular/cdk/accordion';
import { ContactComponent } from './components/contact/contact.component';
import { AnotherTemplateComponent } from './components/sample-template/another-template/another-template.component';
import { PostTemplateComponent } from './components/sample-template/post-template/post-template.component';
import { SampleTemplate2Component } from './components/sample-template/sample-template2/sample-template2.component';
import { SampleTemplate3Component } from './components/sample-template3/sample-template3.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

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
    AnotherTemplateComponent,
    PostTemplateComponent,
    SampleTemplate2Component,
    SampleTemplate3Component,
    NotFoundComponent
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
    ProductService,
    ProductRatingService,
    MarketService,
    MarketcommentService,
    MarketRatingService,
    UserService,
    ProductRatingService,
    ProductCommentService,
    SellerRatingService,
    DatePipe
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
