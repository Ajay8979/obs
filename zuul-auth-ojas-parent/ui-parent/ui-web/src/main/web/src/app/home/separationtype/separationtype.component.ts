import { Component, OnInit } from '@angular/core';

import { NgForm} from '@angular/forms';
import { Separationtype} from './separation.model';
import { OrderPipe } from 'ngx-order-pipe';

@Component({
  selector: 'app-separationtype',
  templateUrl: './separationtype.component.html',
  styleUrls: ['./separationtype.component.scss']
})
export class SeparationtypeComponent implements OnInit {
  value: boolean;
  data; 
  separation: any;
  separationType;
  sortedCollection: any[];

  separationtype: any;
  isEditable:boolean = false;
  reverse: boolean = false;
  order: string = 'separationType';

  
  separationtypelist: Separationtype[] = [
    {
      'separationType': 'obsconed',
      
    },
    {
      'separationType': 'resigned',
      
    }


  ]
  constructor(private orderpipe:OrderPipe) 
  {
    this.sortedCollection = orderpipe.transform(this.separationtypelist, 'separationType');
    console.log(this.sortedCollection);
   }
  
  ngOnInit() {
  }
 

  saveBu(){
    debugger;
    this.value = false;
    this.data = {
      "separationType":this.separationtype,
    
    }
    this.separationtypelist.unshift(this.data);

    this.separationType='';
  
  }
  editData(blist){
    console.log(blist);
    //this.listDetails = blist;
    this.separation = blist.separationType;
    
  }
  saveData(){
    var editD = {
      "separationType":this.separation
    }
    console.log(editD);
  }
  deleterow(index){
    debugger;
    if(index !== -1){
      this.separationtypelist.splice(0,1);
    }
    else {
    this.separationtypelist.splice(index,1);
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
