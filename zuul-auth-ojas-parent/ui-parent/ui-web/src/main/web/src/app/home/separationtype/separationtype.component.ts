import { Component, OnInit } from '@angular/core';

import { NgForm} from '@angular/forms';
import { Separationtype} from './separation.model';
import { OrderPipe } from 'ngx-order-pipe';
import { HrmsService } from '../services/hrms.service';
import swal from 'sweetalert';

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

  
  
  constructor(private hrms:HrmsService) 
  {

  }
  
  
  ngOnInit() {
    this.getSeparation();
  }
 

//--------------Separation Starts------
//---getSeparation-------------
separationDetailsList:any;
separationDetails:any;
  getSeparation() {
    var requestgetObj =
    {
      "separationType":[
              
              ],
              "sessionId":"1234",
              "transactionType":"getall"
      }
    this.hrms.getSeperationType(requestgetObj).subscribe(res =>{
      this.separationDetails = res;
      console.log(this.separationDetails);
      this.separationDetailsList = this.separationDetails.separationTypeList;
      console.log(this.separationDetailsList);
    })
  }
  
//--Save Separation Type--------------------------------
//value: boolean;
//isEditable:boolean = false;
//reverse: boolean = false;

saveseparationReq:any;
separationtypeid:any;
setSeparation(){
  var requestsaveData = 
  {
    "separationType":[
            {
            // "separationTypeId":this.separationtypeid,
            "separationType":this.separationtype
            
            }
            ],
            "sessionId":"1234",
            "transactionType":"save"
    
    
    }
this.hrms.saveSeperationType(requestsaveData).subscribe(response =>{
this.saveseparationReq = response;
console.log(this.saveseparationReq); if(this.saveseparationReq.statusMessage == " Record Saved Successfully"){
  this.value = false;
 swal(this.saveseparationReq.statusMessage, "","success");
 /// this.getPassportCenter();
}
this.getSeparation();
})
   this.getSeparation();
 
}

//--Update Separation Type--------------------------------
updatedseparationRes:any;
separationTypeListArr:any;

updateSeparation(separation){
  
  var updateRequestData = 
  {
    "separationType":[
            {
            "separationTypeId":separation.separationTypeId,
            "separationType":separation.separationType
            
            }
            ],
            "sessionId":"1234",
            "transactionType":"update"
    
    
    }
  this.hrms.updateSeperationType(updateRequestData).subscribe(res =>{
    this.updatedseparationRes = res;
    this.separationTypeListArr=this.updatedseparationRes.separationTypeList;
    console.log(this.updatedseparationRes);

    if(this.updatedseparationRes.statusMessage == "Record Updated Successfully"){
      swal(this.updatedseparationRes.statusMessage , "","success");
      //this.getSeparation();
    }
    this.getSeparation();
  })
}


//--End of Separation Type--------------------------------
}