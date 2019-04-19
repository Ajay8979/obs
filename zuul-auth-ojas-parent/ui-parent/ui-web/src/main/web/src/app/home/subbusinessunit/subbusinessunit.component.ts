import { Component, OnInit } from '@angular/core';
import { Businessunit } from './subbusinessunit.model';
import { NgForm} from '@angular/forms';
import {Costcenter} from './subbusinessunit.model';
import { HrmsService } from '../services/hrms.service';

@Component({
  selector: 'app-subbusinessunit',
  templateUrl: './subbusinessunit.component.html',
  styleUrls: ['./subbusinessunit.component.scss']
})
export class SubbusinessunitComponent implements OnInit {
  updateSubbusinessDetails: Object;
  costCenterId: any;
  businessUnitId: any;
  coscentergetlist: any;
  costCenterList: any;
  businessUnitList: any;
  businessunitDetails: any;
  subBusinessUnitlist: any;
  subbusinessunitDetails: any;
  subbusinessunitRes: any;
  subbusinessunit: any;
  businessunit: any;
  costcenter: any;
  requestData: { "businessUnit": any; "costCenter": any; "subbusinessUnit": any; };
  value: boolean;
  // data: Businessunit; 
  // business: any;
  // businessu: any;
  // costCenter: string;
   //businessUnit: string;

  // businessunit: any;
  // subbusinessunit: any;
  isEditable:boolean = false;
  reverse: boolean = false;
  // costcenterlist: Costcenter[] = [
  //   {costCenter2: 13000},
  //   {costCenter2: 12000},
  //   {costCenter2: 11000},
  //   {costCenter2: 141516},
  //   {costCenter2: 171819}
    
    
  // ]
  // businessunitlist: Businessunit[] = [
  //   {
  //     'businessUnit': 'Sales',
  //     'costCenter': 332000,
  //     'subbusinessUnit':'IT' 
  //   },
  //   {
  //     'businessUnit': 'Staffing',
  //     'costCenter': 23300,
  //     'subbusinessUnit':'oracle'
  //   },
  //   {
  //     'businessUnit': 'IT',
  //     'costCenter': 9000,
  //     'subbusinessUnit':'java'
  //   }


  // ]
  constructor(private hrms:HrmsService) { }
  
  ngOnInit() {
    this.getSubBusinessUnit();
    this.getBusinessunit();
    this.getCostCenter(); 
  }
  // saveBu(){
  //   debugger;
  //   this.value = false;
  //   this.data = {
  //     "businessUnit":this.businessunit,
  //     "costCenter":this.costcenter,
  //     "subbusinessUnit":this.subbusinessunit,
  //   }
  //   this.businessunitlist.unshift(this.data);

  //   this.businessUnit='';
  //   this.costCenter='';
  //   this.subbusinessUnit='';
  
  // }
  // editData(blist: { businessUnit: any; costCenter: any;subbusinessUnit: any; }){
  //   console.log(blist);
  //   this.business = blist.businessUnit;
  //   this.costCenter = blist.costCenter;
  //   this.businessu = blist.subbusinessUnit;
  // }
  // saveData(){
  //   var editD = {
  //     "businessUnit":this.business,
  //     "subbusinessUnit":this.businessu,
  //     "costCenter":this.costCenter
  //   }
  //   console.log(editD);
  // }
  // deleterow(index){
  //   debugger;
  //   if(index !== -1){
  //     this.businessunitlist.splice(0,1);
  //   }
  //   else {
  //   this.businessunitlist.splice(index,1);
  //   }
  // }
  // cancelbulist(){
  //   this.value=false;
    
  // }

  setSubbusinessunit(){
    var requestData = {
      "subBusinessUnitModel": {
        "costCenterId" : this.costCenterId,
        "businessUnitId" : this.businessUnitId,
                  "name": this.subbusinessunit
                },
      "sessionId":"123",
      "transactionType" : "save" 
     }

    this.hrms.setSubbusinessunit(requestData).subscribe(response =>{
      this.subbusinessunitRes = response;
      console.log(this.subbusinessunitRes);
    })
  }
  getSubBusinessUnit(){
    var request = {
      "subBusinessUnitModel": {
     },
      "sessionId": "123"
     }
     this.hrms.getSubbusinessUnit(request).subscribe(res =>{
      this.subbusinessunitDetails = res;
      this.subBusinessUnitlist = this.subbusinessunitDetails.subBusinessUnitModelilist;
      console.log(this.subbusinessunitDetails);
     })
  }
  //bulist getdata
  getBusinessunit() {
    var request = 
      {
        "businessUnit" :{
        },
                "transactionType" : "getAll",
        "sessionId" : "132"
  }
  this.hrms.getBusinessunit(request).subscribe(res =>{
    this.businessunitDetails = res;
    this.businessUnitList = this.businessunitDetails.businessUnitList;
    console.log(this.businessunitDetails);
  })
    }
    //costcenter get
    getCostCenter() {
      var request = {
          "costCenter" : {
          },
          "sessionId" : "123",
         
          "transactionType" : "get"
      }
      this.hrms.getCostcenter(request).subscribe(res =>{
     this.costCenterList = res;
     this.coscentergetlist = this.costCenterList.listOfCostCenter;
     console.log(this.costCenterList);
      })
    }
    saveUpdatedValues(bulist){
      var request = {
        "subBusinessUnitModel": {
          
                  "id":bulist.id,
                "name":bulist.name,
                "businessUnitId":bulist.businessUnitId,
                "costCenterId":bulist.costCenterId
                
        
       },
       "sessionId":"123",
       "transactionType":"update"
       
       }
       this.hrms.updateSubbusinessUnit(request).subscribe(res =>{
         this.updateSubbusinessDetails = res;
         console.log(this.updateSubbusinessDetails);
       })
    }
}