import { CommonModule } from '@angular/common';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-facility-details',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './facility-details.component.html',
  styleUrl: './facility-details.component.css'
})
export class FacilityDetailsComponent implements OnInit{

  ngOnInit(): void {
    const credentials = localStorage.getItem("basic");
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'Basic ' + credentials)
    fetch('http://localhost:8081/api/bank/commons/currencies', {
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
          this.currencies = data.data;
          
        })
        .catch(error => {
          console.error('There was a problem with the fetch operation:', error);
        });
  }

  currencies:Array<{currencyUuid: string,currencyName: string,}>=[]

  @Output() facility: EventEmitter<{
    facilityDetailUuid: string,
    facilityType: string,
    category: string,
    valuationType: string,
    term: number,
    currency: {
      currencyUuid: string,
      currencyName: string,
    },
    amount: number,
    housingLoan: boolean
  }> = new EventEmitter();

  facilityDetailUuid:string= "";
  facilityType: string=""
  category: string="";
  valuationType:string= "";
  term:number= 0;
  currencyUuid: string= "";
  currencyName: string= "";
  amount:number= 0;
  housingLoan:boolean= false;
  
  facilityEmit(){
    this.facility.emit(
      {
        facilityDetailUuid: this.facilityDetailUuid,
        facilityType: this.facilityType,
        category: this.category,
        valuationType: this.valuationType,
        term:this.term,
        currency :{
          currencyUuid: this.currencyUuid,
          currencyName: this.currencyName,
        },
        amount: this.amount,
        housingLoan: this.housingLoan
      }
    )
  }

  setHosingLoan(event:any){
    this.housingLoan=event.target.value
    this.facilityEmit()
  }
  setAmmount(event:any){
    this.amount=event.target.value
    this.facilityEmit()
  }

  setCurrency(event:any){
    this.currencyUuid=event.target.value
    this.facilityEmit()
  }

  setTerm(event:any){
    this.term=event.target.value
    this.facilityEmit()
  }
  
  setPurpose(event:any){
    this.valuationType=event.target.value
    this.facilityEmit()
  }

  setCategory(event:any){
    this.category=event.target.value
    this.facilityEmit()
  }

  setFacilityType(event:any){
    this.facilityType=event.target.value
    this.facilityEmit()
  }

  childFunction() {
    console.log( {
      facilityDetailUuid: this.facilityDetailUuid,
      facilityType: this.facilityType,
      category: this.category,
      valuationType: this.valuationType,
      term:this.term,
      currency :{
        currencyUuid: this.currencyUuid,
        currencyName: this.currencyName,
      },
      amount: this.amount,
      housingLoan: this.housingLoan
    })
  
    this.facilityDetailUuid= "";
    this.facilityType=""
    this.category="";
    this.valuationType= "";
    this.term= 0;
    this.currencyUuid= "";
    this.currencyName= "";
    this.amount= 0;
    this.housingLoan= false;
  }
}
