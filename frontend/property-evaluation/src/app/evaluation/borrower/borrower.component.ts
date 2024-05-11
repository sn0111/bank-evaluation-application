import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-borrower',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './borrower.component.html',
  styleUrl: './borrower.component.css'
})
export class BorrowerComponent {

  @Input() borrowersUuid:string=''
  @Input() index=0;
  customerNumber:string=''
  customerName:string=''
  address:string=''
  contactNumber:string=''
  email:string=''

  @Output() deleteBorrower = new EventEmitter<number>
  @Output() borrower= new EventEmitter<{

    borrowersUuid: string,
    customerNumber: string,
    customerName: string,
    address: string,
    contactNumber: string,
    email: string
  }>

  showAlert:boolean=false;
  message:string="";

  showAlertMessage(message:string){
    this.showAlert=true
    this.message=message
    setInterval(()=>{
      this.showAlert=false
    },2000)
  }

  customers:Array<{
    customerUuid: string;
    customerNumber: string;
    shortName: string;
    isIndividual: string;
    nationality: string;
    nationalityNumber: string;
    nationalityDescription: string;
    streetAddress: string;
    addressLine2: string;
    address_line3: string;
    townCountry: string;
    postCode: string;
    country: string;
    countryCode: string;
    countryCodeNumber: string;
    dispatchCode: string;
    communicationChannel: string;
    phoneNumber: string;
    officePhoneNumber: string;
    faxNumber: string;
    mobileOperatorIso: string;
    mobileOperatorCode: string;
    smsNumber: string;
    email: string;
  }>=[]

  setAddress(event:any){
    this.address= event.target.value
    this.borrower.emit(
      {
        borrowersUuid: this.borrowersUuid,
        customerNumber: this.customerNumber,
        customerName: this.customerName,
        address: event.target.value,
        contactNumber: this.contactNumber,
        email: this.email
      }
    )
  }


  setCustomerNumber(event:any){
    let index = this.customers.findIndex(c=>c.customerNumber===event.target.value)
    if(index!=-1){ 
      this.customerNumber=this.customers[index].customerNumber;
      this.customerName=this.customers[index].shortName;
      this.address=this.customers[index].streetAddress;
      this.contactNumber=this.customers[index].phoneNumber;
      this.email=this.customers[index].email;
    }else{
      const credentials = localStorage.getItem("basic");
      const headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Authorization', 'Basic ' + credentials)
      fetch('http://localhost:8081/api/bank/customers/'+event.target.value, {
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
            this.customers = data.data;
          })
          .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
          });
    }
    this.customerNumber= event.target.value
    this.borrower.emit(
      {
        borrowersUuid: this.borrowersUuid,
        customerNumber: event.target.value,
        customerName: this.customerName,
        address: this.address,
        contactNumber: this.contactNumber,
        email: this.email
      }
    )
  }


  setCustomerName(event:any){
    this.customerName= event.target.value
    this.borrower.emit(
      {
        borrowersUuid: this.borrowersUuid,
        customerNumber: this.customerNumber,
        customerName: this.customerName,
        address: this.address,
        contactNumber: event.target.value,
        email: this.email
      }
    )
  }


  setEmail(event:any){
    this.email= event.target.value
    this.borrower.emit(
      {
        borrowersUuid: this.borrowersUuid,
        customerNumber: this.customerNumber,
        customerName: this.customerName,
        address: this.address,
        contactNumber: this.contactNumber,
        email: event.target.value
      }
    )
  }


  setContactNo(event:any){
    this.contactNumber= event.target.value
    this.borrower.emit(
      {
        borrowersUuid: this.borrowersUuid,
        customerNumber: this.customerNumber,
        customerName: this.customerName,
        address: this.address,
        contactNumber: event.target.value,
        email: this.email
      }
    )
  }

  deleteJointBorrower(){
    this.deleteBorrower.emit(this.index)
  }

  childFunction() {
    this.customerNumber="";
    this.customerName="";
    this.address= "";
    this.contactNumber="";
    this.email="";
  }
}
