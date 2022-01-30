import { MarketRatingComponent } from './components/market-rating/market-rating.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalendarComponent } from './components/calendar/calendar.component';
import { HomeComponent } from './components/home/home.component';
import { MarketCommentComponent } from './components/market-comment/market-comment.component';
import { MarketComponent } from './components/market/market.component';
import { SampleTemplateComponent } from './components/sample-template/sample-template.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'sample', component: SampleTemplateComponent },
  { path: 'calendar', component: CalendarComponent },
  { path: 'market', component: MarketComponent },
  { path: 'marketComment', component: MarketCommentComponent },
  { path: 'marketRating', component: MarketRatingComponent },
  // {path:'**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
