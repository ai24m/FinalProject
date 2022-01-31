import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})

export class UserComponent implements OnInit {
  newUser: User = new User();
  user: User | null = null;
  new: boolean = false;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.newUser = this.newUser;
  }

  load(){

  }

  register(user: User) {
    this.authService.register(user).subscribe({
      next: () => {
        this.router.navigateByUrl('/home');
      },
      error: (fail) => {
        console.error('Register Component Error' + fail)
      }
    })
    console.log(user + 'registered');
  }

  login(user: User) {
    this.authService.login(user.username, user.password).subscribe({
      next: () => {
        this.router.navigateByUrl('profile');
      },
      error: (fail) => {'Login Component fail'}
    })
  }
}
