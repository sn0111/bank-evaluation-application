import { Component, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { BorrowerComponent } from './borrower/borrower.component';
import { InitiatorDetailsComponent } from './initiator-details/initiator-details.component';
import { FacilityDetailsComponent } from './facility-details/facility-details.component';
import { PropertyValuationComponent } from './property-valuation/property-valuation.component';
import { CommentsComponent } from './comments/comments.component';
import { UploadDocumentsComponent } from './upload-documents/upload-documents.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../header/header.component';
import { AlertComponent } from '../alert/alert.component';
import { LoaderComponent } from '../loader/loader.component';

@Component({
  selector: 'app-evaluation',
  standalone: true,
  imports: [BorrowerComponent, AlertComponent, LoaderComponent, InitiatorDetailsComponent, FacilityDetailsComponent, PropertyValuationComponent, CommentsComponent, UploadDocumentsComponent,CommonModule, FormsModule, HeaderComponent],
  templateUrl: './evaluation.component.html',
  styleUrl: './evaluation.component.css'
})
export class EvaluationComponent {
  initiatorContactNumber:string="";
  fosRefNumber:string="";
  evaluationType:string="";
  comment:string="";
  isLoading:boolean=false;

  borrowers:Array<{
    borrowersUuid: string,
    customerNumber: string,
    customerName: string,
    address: string,
    contactNumber: string,
    email: string
  }>=[]

  facility:{
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
  }={
    facilityDetailUuid: "",
    facilityType: "",
    category: "",
    valuationType: "",
    term: 0,
    currency: {
      currencyUuid: "",
      currencyName: "",
    },
    amount: 0,
    housingLoan: false
  }

  documents:Array<{documentsUuid:string}>=[];

  jointBorrowerCount=new Array(1).fill('');

  @ViewChild('initComponent') initComponent: any;
  @ViewChild('facilityComponent') facilityComponent: any;
  @ViewChild('valuationComponent') valuationComponent: any;
  @ViewChild('commentComponent') commentComponent: any;
  @ViewChildren(BorrowerComponent) borrowerComponents: any;

  showAlert:boolean=false;
  message:string="";
  type:string="";

  showAlertMessage(message:string, type:string){
    this.showAlert=true
    this.message=message
    this.type= type
    setInterval(()=>{
      this.showAlert=false
    },2000)
    return;
  }

  callChildFunction() {
    this.initComponent.childFunction();
    this.facilityComponent.childFunction();
    this.valuationComponent.childFunction();
    this.commentComponent.childFunction();
    this.borrowerComponents.forEach((child:any)=> child.childFunction());
  }

  setInitiatorContactNumber(number:string){
    this.initiatorContactNumber=number
  }


  setDescription(comment:string){
    this.comment=comment
  }

  addJointBorrower(){
    this.jointBorrowerCount.push("");
  }

  addBorrower(borrower:{
    borrowersUuid: string,
    customerNumber: string,
    customerName: string,
    address: string,
    contactNumber: string,
    email: string
  }){
    console.log(this.borrowers)
    let index = this.borrowers.findIndex(b=>b.borrowersUuid===borrower.borrowersUuid)
    if(index!=-1){
      this.borrowers[index]=borrower
    }else{
      this.borrowers.push(borrower)
    }
  }

  facilityDetails(facility:{
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
  }){
    this.facility = facility;
  }

  deleteBorrower(index:number){
    this.jointBorrowerCount.splice(index,1)
  }

  setValuation(valuation:{fosRefNumber:string,evaluationType:string}){
    this.fosRefNumber=valuation.fosRefNumber
    this.evaluationType=valuation.evaluationType
  }

  clear(){
    this.callChildFunction()
  }

  setDocuments(documentsUuid:string){
    this.documents.push({documentsUuid})
  }

  create(){
    if(this.initiatorContactNumber===""){
      this.showAlertMessage("Initiator contact number is mandatory","warning")
    }else if(this.facility.facilityType===""){
      this.showAlertMessage("Facility type is mandatory","warning")
    }else if(this.facility.category===""){
      this.showAlertMessage("Faility category is mandatory","warning")
    }else if(this.facility.valuationType===""){
      this.showAlertMessage("Facility purpose is mandatory","warning")
    }else if(this.facility.term<=0){
      this.showAlertMessage("Facility term is mandatory","warning")
    }else if(this.facility.currency.currencyUuid===""){
      this.showAlertMessage("Facility currency is mandatory","warning")
    }else if(this.facility.amount<=0){
      this.showAlertMessage("Facility amount is mandatory","warning")
    }else if(this.evaluationType===""){
      this.showAlertMessage("Property valuation is mandatory","warning")
    }
    this.isLoading=true;
    const body = {
      "initiatorName": localStorage.getItem("userName"),
      "initiatorBusinessUnit": localStorage.getItem("businessUnit"),
      "initiatorContactNumber": this.initiatorContactNumber,
      "facilityDetails": this.facility,
      "evaluationType": this.evaluationType,
      "fosRefNumber": this.fosRefNumber,
      "borrowersDetails": this.borrowers,
      "comments": [
        {
          // "commentsUuid": "string",
          "description": this.comment
        }
      ],
      "fosRef": true,
      "documents":this.documents
    }
    
    // console.log(body)
    const credentials = localStorage.getItem("basic");
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'Basic ' + credentials)
    fetch('http://localhost:8081/api/bank/evaluation', {
          method: 'POST',
          headers: headers,
          body:JSON.stringify(body)
        })
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then(data => {
          this.clear()
          this.showAlertMessage("Successfully saved evaluation request","success")
          this.isLoading=false
        })
        .catch(error => {
          console.error('There was a problem with the fetch operation:', error);
          this.isLoading=false
        });
    
  }

}
