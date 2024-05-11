import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { authGuard } from '../auth.guard';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{

  isAuth:boolean=false;

  constructor(private authService: AuthService){
  }

  ngOnInit(): void {
    this.isAuth=this.authService.isAuth.value
  }

  logout(){
    this.authService.signOut()
  }

}
