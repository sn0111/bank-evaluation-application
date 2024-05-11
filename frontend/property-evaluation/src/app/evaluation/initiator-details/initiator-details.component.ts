import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-initiator-details',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './initiator-details.component.html',
  styleUrl: './initiator-details.component.css'
})
export class InitiatorDetailsComponent implements OnInit{

  @Output() initiatorContactNumber= new EventEmitter<string>;

  userName:string= localStorage.getItem('userName') as string;
  businessUnit:string= localStorage.getItem('businessUnit') as string;
  userNumber:string= localStorage.getItem('userNumber') as string;
 
  ngOnInit(): void {
    this.initiatorContactNumber.emit(this.userNumber)
  }

  onsetNumber(event: any){
    this.userNumber=event.target.value
    this.initiatorContactNumber.emit(this.userNumber)
  }

  childFunction() {
    this.userNumber=""
  }
}
