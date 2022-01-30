import { MarketRatingService } from './services/market-rating.service';
import { MarketcommentService } from './services/market-comment.service';
import { MarketService } from './services/market.service';
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
import { MarketComponent } from './components/market/market.component';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { MarketCommentComponent } from './components/market-comment/market-comment.component';
import { MarketRatingComponent } from './components/market-rating/market-rating.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    SampleTemplateComponent,
    CalendarComponent,
    MarketComponent,
    MarketCommentComponent,
    MarketRatingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
  ],
  providers: [
    AuthService,
    MarketService,
    DatePipe,
    MarketcommentService,
    MarketRatingService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
