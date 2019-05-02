import { Component, OnInit } from '@angular/core';
import { HrmsService } from 'src/app/home/services/hrms.service';
import swal from 'sweetalert';

@Component({
  selector: 'app-business-unit',
  templateUrl: './business-unit.component.html',
  styleUrls: ['./business-unit.component.scss']
})
export class BusinessUnitComponent implements OnInit {

  constructor(private hrms:HrmsService,) { }

  ngOnInit() {
    this.getSubBusinessUnit();
    this.getEmployeeStatusData();
    this.getgenderdata();
  }
  // bu starts-------
bu_empid:any;
bu_sbumodel:any;
bu_status:any;
bu_createdby:any;
bu_flag:any;
business_data:any;
empbasic:any;
empbasicinfo:any;

empbu:any;
empbuinfo:any;
businesinfo:any;
updatebusines:any;
updated_emp_info:any;
updatebu:any;
isUPDATEDBY1:any;
CREATEDBY1:any;
businesbyid:any;
businesDetails:any;
createdby1:any;
isempid:any;
isUpdate:any;

addbusinessUnit(){
  this.isUpdate = false;
  
  this.createdby1 = true;
}
public businessUnitObj=
{
"employeeId": "",
"sbu": "",

"createddate": "",
"updateddate": "",
"createdby": "",
"updatedby": "",
"id": "",
"status": "Active"
}




getgenderdata:any;
// getbusines(){
// var businesinfo = {
//   "employeeBUDeatils": [{

//   }],
//     "transactionType" :"getall"
// }
//  this.hrms.getBusinessunit(businesinfo).subscribe(res =>{
// this.empbu =res;
// this.empbuinfo= this.empbu.listCourse;
//  console.log(this.empbu);
//  for(let i=0;i<=this.empbuinfo.length;i++){

//   for(let j=0;j<this.subBusinessUnitlist.length;j++){
//   if(this.empbuinfo[i].gender==this.subBusinessUnitlist[j].id){
//   this.getgenderdata=this.subBusinessUnitlist[j].gender;
//   console.log("GEnder details");
//   console.log(this.getgenderdata);
//   } 
//   }
//   this.empbuinfo[i].gender=this.getgenderdata;
//   console.log("Final Educational Details Array");
//   console.log(this.empbuinfo);
//   }
//  })

// }
employee_Status:any;
employee_statuslist:any;

savebusinesunit(){

var savebusines={
  "employeeBUDeatils": [{
          "employeeId" : this.businessUnitObj.employeeId,
          "sbu" : this.businessUnitObj.sbu,
          "status" : this.businessUnitObj.status,
          "createdby" : this.businessUnitObj.createdby,
  
  }
  ],
    "transactionType" :"save"
}
this.hrms.savebusinesunit(savebusines).subscribe(res =>{
this.businesinfo =res;
//this.businesdetails= this.businesinfo.listCourse;
console.log(this.businesinfo);
if(this.businesinfo.statusMessage == "Successfully BusinessUnit record saved"){
  swal(this.businesinfo.statusMessage, "","success");
}

  this.getEmployeeStatusData();

 })

}
empdelete(empbu){
var delrequest={
  "employeeBUDeatils": [{
          "id" : empbu.id
  }
  ],
    "transactionType" :"delete"
}
this.hrms.deletebusinessunit(delrequest).subscribe(response=>{
  this.business_data=response;
  console.log(this.business_data);
  if(this.business_data.statusMessage =="Successfully BusinessUnit record deleted"){
     swal(this.business_data.statusMessage, "","success");

     this.getEmployeeStatusData();
   }
})

}
updatebusinesunitint(){
  this.isempid = false;

  var updatebusinesunitkt={
    "employeeBUDeatils": [{
            "id" : this.businessUnitObj.id,
            "employeeId" : this.businessUnitObj.employeeId,
            "sbu" : this.businessUnitObj.sbu,
            "status" : this.businessUnitObj.status,
            "updatedby" :this.businessUnitObj.updatedby
    }
    ],
      "transactionType" :"update"
}

  
  this.hrms.updatebusinesunit(updatebusinesunitkt).subscribe(res =>{
    this.updatebusines = res;
    console.log(this.updatebusines);
       if(this.updatebusines.statusMessage == "Successfully BusinessUnit record updated"){
         swal(this.updatebusines.statusMessage, "","success");
        
       this.getEmployeeStatusData();
       }
  })
 }
 
   //bulist getdata
   subbusinessunitDetails:any;
   subBusinessUnitlist:any;
   getSubBusinessUnit(){
    var request = {
         "subBusinessUnitModel": [
         {
         }
    ],
        "transactionType" : "getAll"
}
     this.hrms.getSubbusinessUnit(request).subscribe(res =>{
      this.subbusinessunitDetails = res;
      this.subBusinessUnitlist = this.subbusinessunitDetails.subBusinessUnitList;
      console.log(this.subbusinessunitDetails);
     })
  }
  getEmployeeStatusData(){
    var request={
   
      "transactionType": "getall"
  }
  this.hrms.getEmployeeStatusMaster(request).subscribe(response=>{
    this.employee_Status = response; 
    this.employee_statuslist= this.employee_Status.employeeStatusList;
    console.log("Employee Status");
    console.log(this.employee_statuslist);
  })
  
  }
 
 getbusiness_data_byid(empbu){
  this.isUpdate = true;
  
  
  this.createdby1 = false;
  var buId= empbu.id;
 
  
  var GetUpdatebusines ={
    "employeeBUDeatils": [{
            "id" :buId
            
    }],
      "transactionType" :"getbyid"
}          
  
  
  this.hrms.getbyIdbusines(GetUpdatebusines).subscribe(res =>{
   this.businesbyid=res;
   this.businesDetails=this.businesbyid.listCourse;
   
   this.businessUnitObj=this.businesDetails[0];
   console.log("this.businesinfo",this.businessUnitObj);
  })
  }


// bu ends ///

}
