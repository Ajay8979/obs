import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert';
import { HrmsService } from 'src/app/home/services/hrms.service';

@Component({
  selector: 'app-kye',
  templateUrl: './kye.component.html',
  styleUrls: ['./kye.component.scss']
})
export class KyeComponent implements OnInit {

  constructor(private hrms:HrmsService) { }

  ngOnInit() {
  }

//---Employee KYE details starts--------------

//--- getting Employee KYE details-------------------
Location:any;
empkye:any;
empkyearr:any;
savepassport:any;
getEmpKye()
{
var reqObj = 
{
        "kye" :
        [{

        }],
        "transactionType"     :  "getAll"
}

this.hrms.getEmployeeKyeDetails(reqObj).subscribe(res =>{
this.empkye = res;
console.log(this.empkye);
this.empkyearr = this.empkye.kyeList;
//console.log("welcome to KYE");
 //console.log(this.empkye.kyeList[5].place_of_issue);
 for(let i=0;i<=this.empkyearr.length;i++){
   for(let j=0;j<this.passportCenterList.length;j++){
    if(this.passportCenterList[j].id==this.empkyearr[i].place_of_issue){
     this.savepassport=this.passportCenterList[j].centerName;
     console.log("Loop for Place");
     console.log(this.savepassport);
    }
   }
   this.empkyearr[i].place_of_issue=this.savepassport;
 }


})
}


//-- Getting Passport Deatails -------------
passportCenterDetails:any;
passportCenterList:any;
passport:any;
sample:any;
getPassportCenter() {
  var request =
  {
    "passportList":[
   ],

"sessionId" : "323",
"transaactionType" : "getall"
}
  this.hrms.getPassportCeneter(request).subscribe(res =>{
    this.passportCenterDetails = res;
    console.log(this.passportCenterDetails.body.passportList);
    //this.sample= this.passportCenterDetails.passportList;
    this.passportCenterList = this.passportCenterDetails.body.passportList;
    console.log(this.passportCenterList);
  })
}


//num = this.passport.id



//-- Getting Passport Deatails -------------


//--- saving Employee KYE details-------------------
savekyeres:any;
savekyeresarr:any;
kYE_Type:any;
uan:any;
kYE_address:any;
passport_no:any;
passport_date_of_Issue:any;
passport_date_of_expiry:any;
place_of_issue:any;
passport_address:any;
//employee_Id:any;
///created_by:any;
//value:boolean




saveEmpKye()
{
var savereqObj =
{
"kye" :[{
           "id"                      :  this.empkyedetails.id,
           "kYE_Type"                :  this.empkyedetails.kYE_Type,
           "uan"                     :  this.empkyedetails.uan,
           "kYE_address"             :  this.empkyedetails.kYE_address,
           "passport_no"             :  this.empkyedetails.passport_no,
           "passport_date_of_Issue"  :  this.empkyedetails.passport_date_of_Issue,
           "passport_date_of_expiry" :  this.empkyedetails.passport_date_of_expiry,
           "place_of_issue"          :  this.empkyedetails.place_of_issue,
           "passport_address"        :  this.empkyedetails.passport_address,
           "employee_Id"             :  this.empkyedetails.employee_Id,
           "created_by"              :  this.empkyedetails.created_by
}],
           "transactionType"     :  "save"
}
this.hrms.saveEmployeeKyeDetails(savereqObj).subscribe(res => {
this.savekyeres = res;
console.log(this.savekyeres);


if(this.savekyeres.statusMessage == "record added successfully")
    {
     swal(this.savekyeres.statusMessage,"","success");
     this.getEmpKye();
   }
})

}

//--- deleting Employee KYE details-------------------
deleteKye;any;
deleteKyearr:any;
isCreated:boolean;
deleteEmpKye(kye)
{
var deleteReqKye = 
{
"kye" :[{
      "id"                      :  kye.id,
      
      "updated_by"              :  kye.updated_by
}],
      "transactionType"     :  "delete"
}

this.hrms.deleteEmployeeKyeDetails(deleteReqKye).subscribe (res => {
this.deleteKye = res;
console.log(this.deleteKye);
this.deleteKyearr = this.deleteKye.kye;

 if(this.deleteKye.statusMessage == "record deleted successfully")
 {
  swal(this.deleteKye.statusMessage,"","success");
  this.getEmpKye();
 }
//  this.getEmpKye();
})
}

//--- Updating Employee KYE details-------------------
isUpdate:boolean=false;
editkye:any;
editkyearr:any;
kye:any;

addeditkye(newUserFormExp)
{
  newUserFormExp.reset();
this.isUpdate=false;

this.isCreated = true;

}

public empkyedetails = 
{
"id"                      :  "",
"kYE_Type"                :  "",
"uan"                     :  "",
"kYE_address"             :  "",
"passport_no"             :  "",
"passport_date_of_Issue"  :  "",
"passport_date_of_expiry" :  "",
"place_of_issue"          :  "",
"passport_address"        :  "",
"employee_Id"             :  "",
"created_by"              :  "",
"updated_by"              :  ""
}


//----edit kye details----------------------------------------------------
editkyeDetails:any;
editkyebyid(kye)
{
this.isUpdate = true;
var kyeid = kye.id;
var editempkyeobj = 
{
"kye" :[{
                "id"                      :  kyeid
             
     }],
             "transactionType"     :  "getAll"
}

this.hrms.editEmployeeKyeDetails(editempkyeobj).subscribe(res => {
this.editkye = res;
this.editkyeDetails = this.editkye.kyeList;
this.empkyedetails = this.editkyeDetails[0];
console.log("this.empkyedetails",this.empkyedetails);

})
}

//----update kye details----------------------------------------------------
updatekyeres:any;
updatekyeresarr:any;

updatekye(kye)
{
var updatekyereq =
{
     "kye" :[{
    "id"                      :  this.empkyedetails.id,
    "kYE_Type"                :  this.empkyedetails.kYE_Type,
    "uan"                     :  this.empkyedetails.uan,
   "kYE_address"             :  this.empkyedetails.kYE_address,
   "passport_no"             :  this.empkyedetails.passport_no,
    "passport_date_of_Issue"  :  this.empkyedetails.passport_date_of_Issue,
   "passport_date_of_expiry" :  this.empkyedetails.passport_date_of_expiry,
   "place_of_issue"          :  this.empkyedetails.place_of_issue,
    "passport_address"        :  this.empkyedetails.passport_address,
   "employee_Id"             :  this.empkyedetails.employee_Id,
   "updated_by"              :  this.empkyedetails.updated_by
}],
"transactionType"     :  "update"
}
this.hrms.updateEmployeeKyeDetails(updatekyereq).subscribe(updateres =>{
this.updatekyeres = updateres;
console.log(this.updatekyeres);
this.updatekyeresarr = this.updatekyeres.kye;


if(this.updatekyeres.statusMessage == "record updated successfully")
    {
     swal(this.updatekyeres.statusMessage,"","success");
     this.getEmpKye();
   }
// this.getEmpKye();
})
}


//---- KYE details  Ends----------------------------------------------




}
