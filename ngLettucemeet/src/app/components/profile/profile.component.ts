import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User | null = null;

  constructor(
    private router: Router,
    private userSvc: UserService,
    private auth: AuthService
  ) { }

  ngOnInit(): void {
    this.auth.getCurrentUser().subscribe({
      next: (that) => {
        this.user = that;
      }
    })
  }

}
