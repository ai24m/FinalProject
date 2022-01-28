import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login-or-register',
  templateUrl: './login-or-register.component.html',
  styleUrls: ['./login-or-register.component.css']
})
export class LoginOrRegisterComponent implements OnInit {
  newUser: User = new User();
  user: User | null = null;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {}

  register(user: User) {
    this.authService.register(user).subscribe({
      next: () => {
        this.router.navigateByUrl('/todo');
      },
      error: (fail) => {
        console.error('Register Component Error' + fail)
      }
    })
    console.log(user + 'registered');
  }

  // login(user: User) {
  //   this.authService.login(user.username, user.password).subscribe({
  //     next: () => {
  //       this.router.navigateByUrl('todo');
  //     },
  //     error: (fail) => {'Login Component fail'}
  //   })
  // }
}
