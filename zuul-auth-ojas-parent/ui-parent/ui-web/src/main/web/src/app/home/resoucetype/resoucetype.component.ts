
import { Component, OnInit } from '@angular/core';
import { OrderPipe } from 'ngx-order-pipe';
import { NgForm } from '@angular/forms';
import { Resourcetype } from './resource.model';

@Component({
  selector: 'app-resoucetype',
  templateUrl: './resoucetype.component.html',
  styleUrls: ['./resoucetype.component.scss']
})
export class ResoucetypeComponent implements OnInit {

  

  sortedCollection: any[];
  order: string = 'resourceType'
  value: boolean;
  data; 
  resource: any;

  resourceType;
  
  resourcetype: any;
  isEditable:boolean = false;
  reverse: boolean = false;
  // public businessUnit = {};

  resourcetypelist:Resourcetype[] = [
    {
      'resourceType': 'Permanant',
     
    },
    {
      'resourceType': 'Contract',
    
    },
  

  ]
  
  constructor(private orderpipe : OrderPipe)
{
this.sortedCollection = orderpipe.transform(this. resourcetypelist, 'resourceType');
console.log(this.sortedCollection);
}

  
  ngOnInit() {
  }
  


  

  saveBu(){
    debugger;
    this.value = false;
    this.data = {
      "resourceType":this.resourcetype,
     
    }
    this.resourcetypelist.push(this.data);

    this.resourceType='';
    
  
  }
  editData(blist){
    console.log(blist);
    //this.listDetails = blist;
    this.resource = blist.resourceType;
  
  }
  saveData(){
    var editD = {
      "resourceType":this.resource,
   
    }
    console.log(editD);
  }
  deleterow(index){
    debugger;
    if(index !== -1){
      this.resourcetypelist.splice(0,1);
    }
    else {
    this.resourcetypelist.splice(index,1);
    }
  }
  
  cancelrtlist(){
    this.value=false;
    
  }
  setOrder(value: string) {
    if (this.order === value) {
    this.reverse = !this.reverse;
    }
    
    this.order = value;
    }
}

