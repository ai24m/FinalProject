import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalendarComponent } from './components/calendar/calendar.component';
import { HomeComponent } from './components/home/home.component';
import { ProductCommentComponent } from './components/product-comment/product-comment.component';
import { ProductRatingComponent } from './components/product-rating/product-rating.component';
import { SampleTemplateComponent } from './components/sample-template/sample-template.component';
import { UserComponent } from './components/user/user.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path:'home', component: HomeComponent },
  {path:'sample', component: SampleTemplateComponent },
  {path:'calendar', component: CalendarComponent },
  {path: 'productrating', component: ProductRatingComponent},
  {path: 'productcomment', component: ProductCommentComponent},
  {path:'login', component: UserComponent },
  // {path:'**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
