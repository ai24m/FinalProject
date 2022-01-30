import { MarketRatingComponent } from './components/market-rating/market-rating.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalendarComponent } from './components/calendar/calendar.component';
import { HomeComponent } from './components/home/home.component';
import { ProductComponent } from './components/product/product.component';
import { ProductCommentComponent } from './components/product-comment/product-comment.component';
import { ProductRatingComponent } from './components/product-rating/product-rating.component';
import { MarketComponent } from './components/market/market.component';
import { SampleTemplateComponent } from './components/sample-template/sample-template.component';
import { UserComponent } from './components/user/user.component';
import { SellerRatingComponent } from './components/seller-rating/seller-rating.component';
import { ContactComponent } from './components/contact/contact.component';
import { SampleTemplate2Component } from './components/sample-template2/sample-template2.component';
import { SampleTemplate3Component } from './components/sample-template3/sample-template3.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { MarketIdComponent } from './components/market-id/market-id.component';
import { ProfileComponent } from './components/profile/profile.component';
import { MarketCommentComponent } from './components/market-id/market-comment/market-comment.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: ContactComponent},
  { path: 'login', component: UserComponent },
  { path: 'sample', component: SampleTemplateComponent },
  { path: 'calendar', component: CalendarComponent },
  { path: 'products', component: ProductComponent },
  { path: 'productrating', component: ProductRatingComponent },
  { path: 'productcomment', component: ProductCommentComponent },
  { path: 'sellerrating', component: SellerRatingComponent },
  { path: 'market', component: MarketComponent },
  { path: 'market/:id', component: MarketIdComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'sample2', component: SampleTemplate2Component },
  { path: 'sample3', component: SampleTemplate3Component },
  // { path: 'marketcomment', component: MarketCommentComponent },
  { path: 'marketrating', component: MarketRatingComponent },
  { path:'**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule { }
