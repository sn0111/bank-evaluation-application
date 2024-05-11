import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { CommonModule } from '@angular/common';
import { formatDate } from '../date.format';

@Component({
  selector: 'app-validation-requests',
  standalone: true,
  imports: [HeaderComponent, CommonModule],
  templateUrl: './validation-requests.component.html',
  styleUrl: './validation-requests.component.css'
})
export class ValidationRequestsComponent implements OnInit{

  requests:Array<{reference:string,createdDate:string,updatedDate:string,initiatorName:string,fosRefNumber:string}>=[];

  ngOnInit(): void {
    const credentials = localStorage.getItem("basic");
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'Basic ' + credentials)
    fetch('http://localhost:8081/api/bank/evaluations', {
          method: 'GET',
          headers: headers,
        })
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then(data => {
          this.requests = data.data;
          console.log(this.requests)
        })
        .catch(error => {
          console.error('There was a problem with the fetch operation:', error);
        });
  }

  dateFormat(date:string){
    return formatDate(date)
  }
}
