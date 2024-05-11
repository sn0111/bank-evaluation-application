import { CommonModule } from '@angular/common';
import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { formatDate } from '../../date.format';
import { AlertComponent } from '../../alert/alert.component';
import { LoaderComponent } from '../../loader/loader.component';

@Component({
  selector: 'app-upload-documents',
  standalone: true,
  imports: [CommonModule, FormsModule, AlertComponent, LoaderComponent],
  templateUrl: './upload-documents.component.html',
  styleUrl: './upload-documents.component.css'
})
export class UploadDocumentsComponent implements OnInit{
  showAlert:boolean=false;
  message:string="";
  type:string="";
  isLoading:boolean=false;

  showAlertMessage(message:string, type:string){
    this.showAlert=true
    this.message=message
    this.type= type
    setInterval(()=>{
      this.showAlert=false
    },10000)
    return;
  }

  documentTypes:Array<{documentUuid:string,documentName:string}>=[]
  documentUuid:String="";
  files:Array<{documentsUuid:string,name:string, size:string,documentType: {documentUuid:string, documentName:string},url:string, createdDate:string, createdBy:string}>=[];

  @Output() document= new EventEmitter<string>;
  ngOnInit(): void {
    
    const credentials = localStorage.getItem("basic");
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'Basic ' + credentials)
    fetch('http://localhost:8081/api/bank/commons/document-types', {
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
          this.documentTypes = data.data;
        })
        .catch(error => {
          console.error('There was a problem with the fetch operation:', error);
        });
  }

  @ViewChild('fileInput') fileInput: any;
  constructor() { 
    
  }

  openFileInput() {
    this.fileInput.nativeElement.click();
  }

  saveDocument(file:any){
    const format = file.name.split(".")[1].toLowerCase()
    console.log(format)
    if(format != "xlsx" && format!="pdf"){
      this.showAlertMessage("Document supported formats only xlsx, pdf","warning")
      return;
    }else if(this.documentUuid==="" || this.documentUuid==null || this.documentUuid==undefined ){
      this.showAlertMessage("Please select the uploaded document type","warning")
      return;
    }
    this.isLoading=true
    const credentials = localStorage.getItem("basic");
    const headers = new Headers();
    // headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'Basic ' + credentials)
    const formdata = new FormData();
    formdata.append("file", file, file.name);
    fetch('http://localhost:8081/api/bank/evaluation/document/'+this.documentUuid, {
          method: 'POST',
          headers: headers,
          body:formdata
        })
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then(data => {
          const file = data.data
          const fileSizeInBytes = file.size;
          const fileSizeInMB = fileSizeInBytes / (1024 * 1024);
          file['size'] = fileSizeInMB.toFixed(3)
          file['createdDate'] = this.dateFormat(new Date().toString());
          this.files.push(data.data)
          this.document.emit(file.documentsUuid)
          this.isLoading=false
        })
        .catch(error => {
          console.error('There was a problem with the fetch operation:', error);
          this.isLoading=false
        });
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      console.log('Selected file:', file.name);
      this.saveDocument(file)
    }
  }

  onDragOver(event: any) {
    event.preventDefault();
  }

  onDrop(event: any) {
    event.preventDefault();
    const files = event.dataTransfer.files;
    if (files.length > 0) {
      console.log('Dropped file:', files[0].name);
      this.saveDocument(files[0])
    }
  }

  dateFormat(date:string){
    return formatDate(date)
  }

  deleteDocument(uuid:string){
    
    const credentials = localStorage.getItem("basic");
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'Basic ' + credentials)
    this.isLoading=true
    fetch('http://localhost:8081/api/bank/evaluation/document/'+uuid, {
          method: 'DELETE',
          headers: headers,
        })
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then(data => {
          this.files = this.files.filter(o=>o.documentsUuid!==uuid)
        })
        .catch(error => {
          console.error('There was a problem with the fetch operation:', error);
          this.isLoading=false
        });
  }
}
