import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as $ from 'jquery';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  title = 'angularbootstrap';


  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit() {

    $("#menu-toggle").click(function (e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  }

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }

  logout() {
    this.auth.logout();
    this.router.navigateByUrl('/home');
  }

  admin(): boolean {
    this.auth.getCurrentUser().subscribe({
      next: (user) => {
        if (user.role === 'admin') {
          return true;
        } else {
          return false;
        }
      }
    })
    return false;
  }

}

