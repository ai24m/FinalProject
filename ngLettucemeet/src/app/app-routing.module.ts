import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalendarComponent } from './components/calendar/calendar.component';
import { HomeComponent } from './components/home/home.component';
import { ProductComponent } from './components/product/product.component';
import { ProductCommentComponent } from './components/product-id/product-comment/product-comment.component';
import { ProductRatingComponent } from './components/product-id/product-rating/product-rating.component';
import { MarketComponent } from './components/market/market.component';
import { UserComponent } from './components/user/user.component';
import { SellerRatingComponent } from './components/seller-rating/seller-rating.component';
import { ContactComponent } from './components/contact/contact.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { MarketIdComponent } from './components/market-id/market-id.component';
import { ProfileComponent } from './components/profile/profile.component';
import { MarketCommentComponent } from './components/market-id/market-comment/market-comment.component';
import { MarketRatingComponent } from './components/market-id/market-rating/market-rating.component';
import { ProductIdComponent } from './components/product-id/product-id.component';
import { AdminComponent } from './components/admin/admin.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: ContactComponent},
  { path: 'admin', component: AdminComponent},
  { path: 'login', component: UserComponent },
  { path: 'calendar', component: CalendarComponent },
  { path: 'users', component: UserComponent },
  { path: 'products', component: ProductComponent},
  { path: 'products/find/:id', component: ProductIdComponent,
    children: [
      {path: 'comments', component: ProductCommentComponent },
      {path: 'ratings', component: ProductRatingComponent }
    ]
  },
  { path: 'sellerrating', component: SellerRatingComponent },
  { path: 'markets', component: MarketComponent },
  { path: 'markets/:id', component: MarketIdComponent,
    children: [
      { path: 'comments', component: MarketCommentComponent},
      { path: 'ratings', component: MarketRatingComponent}
  ]},
  { path: 'profile', component: ProfileComponent },
  { path:'**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule { }
