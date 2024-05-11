import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [HeaderComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  constructor(private router:Router, private authService:AuthService){

  }

  userEmail:string="";
  password:string="";
  userName:string="";
  businessUnit:string="";
  userNumber:string="";

  showAlert:boolean=false;
  message:string="";

  showAlertMessage(message:string){
    this.showAlert=true
    this.message=message
    setInterval(()=>{
      this.showAlert=false
    },2000)
  }

  ngOnInit(): void {
    
  }

  setUserEmail(event:any){
    this.userEmail=event.target.value
  }

  setPassword(event:any){
    this.password=event.target.value
  }

  login(event:any){
    event.preventDefault()
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    fetch('http://localhost:8081/users/login', {
          method: 'POST',
          headers: headers,
          body:JSON.stringify({
            "userEmail":this.userEmail,
            "password":this.password
          })
        })
        .then(response => response.json())
        .then(res => {
          let user = res.data;
          this.businessUnit=user.businessUnit
          this.userNumber=user.contactNumber
          this.userName=user.userName
          localStorage.setItem("businessUnit",user.businessUnit)
          localStorage.setItem("userName",user.userName)
          localStorage.setItem("userNumber",user.contactNumber)
          localStorage.setItem("basic",btoa(this.userEmail + ':' + this.password))
          this.authService.signIn(this.userEmail)
          // this.router.navigate(["/home"])
        })
        .catch(error => {
          console.error('There was a problem with the fetch operation:', error);
        });
  }

}
