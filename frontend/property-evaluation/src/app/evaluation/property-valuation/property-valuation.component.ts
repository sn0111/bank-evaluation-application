import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-property-valuation',
  standalone: true,
  imports: [],
  templateUrl: './property-valuation.component.html',
  styleUrl: './property-valuation.component.css'
})
export class PropertyValuationComponent implements OnInit{

  reference:number=0;
  @Output() valuation=new EventEmitter<{fosRefNumber:string,evaluationType:string}>
  fosRefNumber:string="";
  evaluationType:string=""

  constructor(){

  }

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
    this.referenceNo()
  }

  referenceNo(){
    const credentials = localStorage.getItem("basic");
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'Basic ' + credentials)
    fetch('http://localhost:8081/api/bank/commons/reference', {
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
          this.reference = data.data.referenceNumber;
          var currentDate = new Date();
          var currentYear = currentDate.getFullYear();
          var currentMonth = (currentDate.getMonth() + 1).toString().padStart(2, '0'); // Adding 1 to make it 1-indexed
          
          this.fosRefNumber=currentYear+"/"+currentMonth+"/"+(this.reference+1).toString().padStart(3,'0')
        })
        .catch(error => {
          console.error('There was a problem with the fetch operation:', error);
        });
  }

  setEvaluationType(event:any){
    this.evaluationType=event.target.value
    this.valuation.emit({fosRefNumber:this.fosRefNumber,evaluationType:this.evaluationType})
  }

  setFosRefNumber(event:any){
    this.fosRefNumber=event.target.value
    this.valuation.emit({fosRefNumber:this.fosRefNumber,evaluationType:this.evaluationType})
  }

  childFunction() {
    this.fosRefNumber="";
    this.evaluationType=""
    this.referenceNo()
  }
}
