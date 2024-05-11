import { CommonModule } from '@angular/common';
import { Component, EventEmitter, HostListener, OnInit, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {formatDate} from '../../date.format'

@Component({
  selector: 'app-comments',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './comments.component.html',
  styleUrl: './comments.component.css'
})
export class CommentsComponent implements OnInit{
  @Output() comment = new EventEmitter<string>();
  description:string="";
  // comments:Array<{"createdDate":string,"updatedDate":string,"createdBy":number,"updatedBy":number,"commentsUuid":string,"description":string}> =[{"createdDate":"2024-05-08 00:18:55.628","updatedDate":"2024-05-08 00:18:55.628","createdBy":1,"updatedBy":1,"commentsUuid":"e6a30ca5-706f-40b2-8c7e-500850916de0","description":"First comment"},{"createdDate":"2024-05-08 00:19:54.569","updatedDate":"2024-05-08 00:19:54.569","createdBy":1,"updatedBy":1,"commentsUuid":"44d8d228-fd5f-4faf-98de-2b4f46eff44a","description":"First comment"},{"createdDate":"2024-05-08 00:23:39.776","updatedDate":"2024-05-08 00:23:39.776","createdBy":1,"updatedBy":1,"commentsUuid":"98d035a5-a1e5-4544-9d4b-a41d0c50b0a3","description":"First comment"},{"createdDate":"2024-05-08 22:58:13.835","updatedDate":"2024-05-08 22:58:13.835","createdBy":1,"updatedBy":1,"commentsUuid":"b36653ad-8d62-4bbb-9709-01b42396e281","description":"comments"}];
  comments:Array<{"createdDate":string,"updatedDate":string,"createdBy":string,"updatedBy":number,"commentsUuid":string,"description":string}>=[];
 

  ngOnInit(): void {

    const credentials = localStorage.getItem("basic");
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'Basic ' + credentials)
    fetch('http://localhost:8081/api/bank/evaluation/comments', {
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
          this.comments = data.data;
        })
        .catch(error => {
          console.error('There was a problem with the fetch operation:', error);
        });
  }

  onDescriptionChange(event:any){
    this.description=event.target.value
    this.comment.emit(event.target.value);
  }

  dateFormat(date:string){
    return formatDate(date)
  }

  childFunction() {
    this.description=""
  }
}
