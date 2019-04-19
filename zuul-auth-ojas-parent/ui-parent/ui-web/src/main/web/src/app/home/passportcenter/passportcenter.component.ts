import { Component, OnInit } from '@angular/core';
import { Passport } from './passportcenter.model';
import { NgForm} from '@angular/forms';
// import { Http2Server } from 'http2';
import { HrmsService } from '../services/hrms.service';

@Component({
  selector: 'app-passportcenter',
  templateUrl: './passportcenter.component.html',
  styleUrls: ['./passportcenter.component.scss']
})
export class PassportcenterComponent implements OnInit {
  centerName: any;
  deletepassportRes: any;
  updatedpassportRes: any;
  passportCenterList: any;
  passportCenterDetails: any;
  passportCenterReq: any;
  value: boolean;
//   public data1=[];
//   public show:boolean = false;
//   public buttonName:any = 'Show';


//   //new code starts here

//   data; 
//   pass:any;
//   passPort;
//   isEditable:boolean = false;
//   reverse: boolean = false;

//   order: string = 'info.name';
 
  

   constructor(private hrms:HrmsService) {
   
    }
//    passportlist:Passport[]=[{
     
//     "passPort": "Thirupathi"
//    },
//  {
//    "passPort": "Rajahmundry"
//  },
//  {
//    "passPort": "Vishakapatnam"
//  },
//  {
//    "passPort": "Hyderabad"
//  },
//  {
//    "passPort": "Amaravathi"
//  },
// ]




//  saveBu(){
//   debugger;
//   this.value = false;
//   this.data = {
//     "passPort":this.pass,
   
//   }
//   this.passportlist.unshift(this.data);

//   this.passPort='';
 

// }
// editData(blist){
//   console.log(blist);
//   //this.listDetails = blist;
//   this.pass = blist.passPort;

// }
// saveData(){
//   var editD = {
//     "passPort":this.pass,
   
//   }
//   console.log(editD);
// }
// deleterow(index){
//   debugger;
//   if(index !== -1){
//     this.passportlist.splice(0,1);
//   }
//   else {
//   this.passportlist.splice(index,1);
//   }
// }

// cancelbulist(){
//   this.value=false;
  
// }
  ngOnInit() {
    this.getPassportCenter();
 }
//   handleClick(event: Event) {
//     console.log('Button clicked', event)
//   }
//   toggle() {
//     this.show = !this.show;

//     // CHANGE THE NAME OF THE BUTTON.
//     if(this.show)  
//       this.buttonName = "Hide";
//     else
//       this.buttonName = "Show";
//   }

//   setOrder(value: string) {
//     if (this.order === value) {
//       this.reverse = !this.reverse;
//     }

//     this.order = value;

// }
//  if(this.empDesignationRes.statusMessage == "Successfully record added"){
//       this.value = false;
//       swal(this.empDesignationRes.statusMessage , "","success");
//       this.getEmpDesignation();
//     }

setPassportCenter(){
  var requestData = {
    "passport" : {
            "centerName" : this.centerName
    }, 
       "transactionType":"save",
    "sessionId" : "any String"
}
this.hrms.setPassportCeneter(requestData).subscribe(response =>{
this.passportCenterReq = response;
console.log(this.passportCenterReq);
 if(this.passportCenterReq.statusMessage == "PassportCenter has saved successfully"){
   this.value = false;
   swal(this.passportCenterReq.statusMessage, "","success");
   this.getPassportCenter();
 }
})
}
getPassportCenter() {
  var request = {
    "transactionType":"getAll",
    "passport" : {
    },
    "sessionId" : "any String"
}
  this.hrms.getPassportCeneter(request).subscribe(res =>{
    this.passportCenterDetails = res;
    this.passportCenterList = this.passportCenterDetails.passportList;
    console.log(this.passportCenterDetails);
  })
}
saveUpdatedValues(bulist){
  console.log(bulist);
  var updateRequestData = {
    "passport" : {
     "id":bulist.id,
            "centerName" : bulist.centerName
    },
    "transactionType":"update",
    "sessionId" : "any String"
    }
  this.hrms.updatePassportCenter(updateRequestData).subscribe(res =>{
    this.updatedpassportRes = res;
    console.log(this.updatedpassportRes);
    if(this.updatedpassportRes.statusMessage == "PassportCenter has updated successfully"){
      swal(this.updatedpassportRes.statusMessage , "","success");
      this.getPassportCenter();
    }
  })
}
deletePassport(bulist){
  var deleteRequest = {
    "passport" : {
            "id": bulist.id,
            "centerName" : bulist.centerName
    },
    "transactionType":"delete",
    "sessionId" : "any String"
}
this.hrms.deletepassportCenter(deleteRequest).subscribe(data =>{
    this.deletepassportRes = data;
    console.log(this.deletepassportRes);
    if(this.deletepassportRes.statusMessage === "PassportCenter has  deleted successfully"){
      swal(this.deletepassportRes.statusMessage , "","success");
      this.getPassportCenter();
    }
  })
}
}
