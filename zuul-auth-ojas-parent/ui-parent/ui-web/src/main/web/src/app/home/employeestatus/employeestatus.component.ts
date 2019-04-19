import { Component, OnInit } from '@angular/core';
import { NgForm} from '@angular/forms';
import { Employeestatus } from './employee.model';
import { OrderPipe } from 'ngx-order-pipe';




// import {Costcenter} from './businessunit.model'

@Component({
     selector: 'app-employeestatus',
     templateUrl: './employeestatus.component.html',
     styleUrls: ['./employeestatus.component.scss']
   })
   export class EmployeestatusComponent implements OnInit {

value: boolean;
data; 
employee: any;
employeeStatus;
sortedCollection: any[];

employeestatus: any;
isEditable:boolean = false;
reverse: boolean = false;
order: string = 'employeeStatus';

  
  

employeestatuslist: Employeestatus[] = 
[
  {
    'employeeStatus': 'Application Development',
  },
  {
    'employeeStatus': 'Support',
  },
  {
    'employeeStatus': 'IDM'
  }


]
constructor(private orderpipe: OrderPipe )
 {
  this.sortedCollection = orderpipe.transform(this.employeestatuslist, 'employeeStatus');
  console.log(this.sortedCollection);

  }

ngOnInit() {
}

saveBu(){
  debugger;
  this.value = false;
  this.data = {
    "employeeStatus":this.employeestatus,
  
  }
  this.employeestatuslist.unshift(this.data);

  this.employeeStatus='';

}
editData(blist){
  console.log(blist);
  //this.listDetails = blist;
  this.employee = blist.employeeStatus;

}
saveData(){
  var editD = {
    "employeeStatus":this.employee
  }
  console.log(editD);
}
deleterow(index){
  debugger;
  if(index !== -1){
    this.employeestatuslist.splice(0,1);
  }
  else {
  this.employeestatuslist.splice(index,1);
  }
}

cancelbulist(){
  this.value=false;
  
}
setOrder(value: string) {
  if (this.order === value) {
    this.reverse = !this.reverse;
  }

  this.order = value;

}
}
