import { AuthService } from './../../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import { HrmsService } from '../../services/hrms.service';
import { identifierModuleUrl } from '@angular/compiler';
import swal from 'sweetalert';
declare var $: any;

@Component({
  selector: 'app-employeeedit',
  templateUrl: './employeeedit.component.html',
  styleUrls: ['./employeeedit.component.scss']
})
export class EmployeeeditComponent implements OnInit {
 public dependent_details:any;
  dependents: any;
  formdata:any;
  dependent_Name: any;
  relation:any;
  date_Of_Birth:any;
  created_By:any;
  employee_Id:any;
  updated_by:any;
  updateRes:any;
  updated_By:number;
  

  deleteRes:any;
  employee_bankdetails:any;
  bankdt: any;
  bankuppdate:any;
  banksave:any;
  bank_account_no:any;
  bank_name:any;
  bank_city:any;
  bank_branch:any;
  bank_ifsc_code:any;
  bank_account_status:any;
  is_active:boolean;
  employee_id:any;
  created_by: any;
  is_bank_update:boolean;
  is_bank_save:boolean;
  employee_bankdetailsById:any;
  bank_details:any;
  employee_dependentdetailsById:any;
  dependent_dtdetails:any;
  employmentdetailsss:any;
 
  Createdby:boolean;
  onboarding:any;
  //onboarding
  employeeId:any;
  joiningDate:Date;
  resourceType:any;
  bondStatus:boolean;
  flag:boolean;
  createdBy:any;
  onboardingdetails:any;
  onbordingArr:any;
  deleteonboarding:any;
  onboard_details:any;;
  onboard_details_by_id:any;
  onboard_details_final:any;
  resignationDate:Date;
  exitDate:Date;
  separationType:any;
  updatedBy:any
  onboardRes:any;
  id:any;
  isupdate:any;
  isSaved:any;
  //project starts
    //project
  
    projectdelete: any;
    projectGetById: any;
    //createdBy: any;
    isInternal: boolean;
    bdmContact: any;
    projectStatus: any;
    projectType: any;
    gstApplicable: any;
    location: any;
    customer: any;
    projectTechStack: any;
    billingId: any;
    endDate: any;
    startDate: any;
    //employeeId: any;
    rateId: any;
    contractId: any;
    projectName: any;
    projectsave: any;
    projectDetailsLi: any;
    projectDetails: any;
    projectDetailsList: any;
    projectdetailsupdate:any;
  
  
  
  //project end
 

  constructor(private hrms:HrmsService, private auth: AuthService) { }

  ngOnInit() {
    this.getdependentData();
    this.getbankdetails();
   this.getEmpExp();
  this.getEmpKye();
     this.getempTiltle();
     this.getEmpEdu();
    this.getContactInformation();
    this.getCertificationDetails();
     this.getSkillInfo();
     this.getbusines();
     this.savebusinesunit();
     this.getempdata();
	   this.getdependentData();
    this.getbankdetails();
   this.getemploymentdetails();
   this.getProject();


  }
//  OnSave()
//  {
   
//   this.createdByDependent=true;
//    this.isupdateDependent=false;
//  }


  onSubmitbu(form: NgForm)
  {
    if (form.valid) 
    {
      console.log(form.value);
    }
    
  }
  onSubmit1(form: NgForm)
  {
    if (form.valid) 
    {
      console.log(form.value);
    }
  }
  onSubmittitle(form: NgForm)
  {
    if (form.valid) 
    {
      console.log(form.value);
    }
  }
  onSubmitDep(form: NgForm)
  {
    //this.formdata=JSON.stringify(form.value);
    if (form.valid) 
    {
      // this.hrms.savedependentdetails(this.formdata).subscribe(response =>{
      //   this.dependent_details = response;
      //       this.dependents = this.dependent_details.saveDependentDetailsList;
     
      //        console.log(this.dependent_details);
      //    })
         
    //  console.log(form.value);
    }
  }
  onSubmit(form: NgForm) 
  {
    if (form.valid) 
    {
      console.log(form.value);
    }
  }
  onSubmitBank(form: NgForm) 
  {
    if (form.valid) 
    {
      console.log(form.value);
    }
  }
  onSubmitCer(form: NgForm) 
  {
    if (form.valid) 
    {
      console.log(form.value);
    }
  }

  onSubmitIns(form: NgForm) 
  {
    if (form.valid) 
    {
      console.log(form.value);
    }
  }

  
getdependentData(){
  var requestData ={
    "dependentDetails" : [
                                            {
                                            }
                   ], 
                   "transactionType":"getall",
                  "sessionId" : "any String" 
  }

    this.hrms.getdependentdetails(requestData).subscribe(response =>{
      this.dependent_details = response;
      this.dependents = this.dependent_details.getDependentDetailsList;

    //  console.log(this.dependent_details);
    //   if(this.costcenterRes.statusMessage == "Successfully record added"){
    //     this.value = false;
    //     swal(this.costcenterRes.statusMessage, "","success");
    //     this.getCostCenter();
    //   }
    })
  }
//  savedependentdata(){
//     var requestData = {
//   "dependentDetails" : [
//     {
//                             "id":3,
// "dependent_Name":"AA",
// "relation":"brother",
// "date_Of_Birth":"1995-04-08",
// "employee_Id":95,
// "updated_By":21
// },

// {
//        "id":4,
// "dependent_Name":"BB",
// "relation":"brother",
// "date_Of_Birth":"1995-04-08",
// "employee_Id":31,
// "updated_By":21
// }
// ], 
// "transactionType":"update",
// "sessionId" : "any String" 
// }
    
//     this.hrms.savedependentdetails(requestData).subscribe(response =>{
//    this.dependent_details = response;
//        this.dependents = this.dependent_details.saveDependentDetailsList;

//         console.log(this.dependent_details);
//     })
    

 
     
//   }
//   onsavedependentdata(){

//     var requestData = {
//       "dependentDetails" : [
//                                     {
//                                     "dependent_Name":this.dependent_Name,
//                                     "relation":this.relation,
//                                     "date_Of_Birth":this.date_Of_Birth,
//                                     "employee_Id":this.employee_Id,
                                   
//                                      "created_By":this.created_By,

//                          }
//                            ], 
//                      "transactionType":"save",
//                     "sessionId" : "any String" 
//     }
    
//     this.hrms.savedependentdetails(requestData).subscribe(response =>{
//    this.dependent_details = response;
//        this.dependents = this.dependent_details.saveDependentDetailsList;

//        // console.log(this.dependent_details);
//         this.getdependentData();
//     })
    
//  }
 updatedependentdata(){
 
  var updatedependentdata ={
    "dependentDetails" : [
                                            {
                                                                    "id":3,
                                  "dependent_Name":this.dependent_Name,
                                  "relation":this.relation,
                                  "date_Of_Birth":this.date_Of_Birth,
                                  "employee_Id":this.employee_Id,
                                  "updated_By":this.updated_By
                       }
                       
                      
                   ], 
                   "transactionType":"update",
                  "sessionId" : "any String" 
  }
  
  this.hrms.updatedependentdetails(updatedependentdata).subscribe(res =>{
    this.updateRes = res;
    console.log(this.updateRes);
      if(this.updateRes.statusMessage == "Successfully record updated"){
        //swal(this.updateRes.statusMessage, "","success");
        this.getdependentData();
      }
  })

 }
 deletedependentdata(dependent){

  var deletedependentdata ={
    "dependentDetails" : [
                      {
                           "id":dependent.id,
                           "updated_By":dependent.updated_By
                       },
                       
                      
                   ], 
                   "transactionType":"delete",
                  "sessionId" : "any String" 
  }
this.hrms.deletedependentdetails(deletedependentdata).subscribe(res=>{
  this.deleteRes=res;
  if(this.deleteRes.statusMessage == "DependentDetails are deleted successfully"){
    //swal(this.deleteRes.statusMessage, "","success");
    this.getdependentData();
  }

})
  
 }
 getbankdetails(){
 var bankdetails=
 {
  "bankDetails":[

  ],
"transactionType":"getall"

}
this.hrms.getbankserverdetails(bankdetails).subscribe(response =>{
  this.employee_bankdetails = response;
  this.bankdt = this.employee_bankdetails.listBankDetails;
console.log(this.bankdt);

})
}







//skill starts
skill_id:any;
level_id:any;
// employee_id:any;
// created_by:any;
skillReq:any;
SkillArr :any;
skillinfolist:any;
skillInfoList:any;
deleteSkilldetails:any;
deleteSkillarr:any;
skillbyid:any;
SkillDetails:any;
update_by:any;
updateArr:any;

//method save skillInfo
setSkillInfo(){

var reqData={
      
"skillInfoModel":
[{
                

                
"skill_id":this.SKILLinfo.skill_id,
          
  "level_id" :this.SKILLinfo.level_id,
           
 "employee_id" :this.SKILLinfo.employee_id,
           
 "created_by" : this.SKILLinfo.created_by,
 "flag":this.SKILLinfo.flag
}],

        
  
  "sessionId" :"14",
        
"transactionType" : "save"
  
  
}

this.hrms.setSkill(reqData).subscribe(responce=>{
  this.skillReq = responce;
  console.log(this.skillReq);
  if(this.skillReq.statusMessage =="Successfully record added"){
    //this.value = false;
   //swal(this.skillReq.statusMessage, "","success");
   this.getSkillInfo();
  }
  this.SkillArr = this.skillReq.getSkillInfoList;
  this.getSkillInfo();
 
});


}

//method get skillInfo
getSkillInfo(){

var Requestdata={
      
  "skillInfoModel" :[ {
  
                  
  
  }],
          "sessionId" : "1234",
  
  "transactionType" : "getAll"
  
  }
this.hrms.getSkill(Requestdata).subscribe(responce=>{
this.skillinfolist=responce;
this.skillInfoList=this.skillinfolist.getSkillInfoList;
console.log(this.skillInfoList)
})

}

//skill delete Info
// deleteSkillInfo(tableData){

//   var deleteRequest= {
//    "skillInfoModel":[{
                

//     "id":tableData.id,
           
//    "update_by" : tableData.update_by
        
//   }],

        
  
//   "sessionId" :"14",
        
//   "transactionType" : "save"
  
  
//   }
//   this.hrms.deleteSkill(deleteRequest).subscribe(request=>{
//     this.deleteSkilldetails=request;
//     this.deleteSkillarr=this.deleteSkilldetails.getSkillInfoList;
//     console.log(this.deleteSkilldetails);
//     this.getSkillInfo();
//   })
// }


addSkillvalue(){
this.isUPDATEDBY=false;
this.CREATEDBY=true;
}
isUPDATEDBY=false;
CREATEDBY=true;

public SKILLinfo={
"skill_id":"",
          
    "level_id" :"",
             
   "employee_id" :"",
             
   "created_by" : "",
              
    "update_by" :"",
     "flag":"",
     "id":""
}

//method edit skillInfo
getdatabyID(tableData){

this.isUPDATEDBY=true;
this.CREATEDBY=false;

console.log(tableData);

 var skillid= tableData.id;

var GetUpdateData ={
      
  "skillInfoModel" :[ {
  
                  "id" :skillid
  
  }],
          "sessionId" : "1234",
  
  "transactionType" : "getById"
  
  }

  

this.hrms.getSkilbyId(GetUpdateData).subscribe(res =>{
 this.skillbyid=res;
 this.SkillDetails=this.skillbyid.getSkillInfoList;
 this.SKILLinfo=this.SkillDetails[0];
 console.log("this.SKILLinfo",this.SKILLinfo)
})
}

//method update skillInfo
updateSkilldetails(SKILLinfo){

var updaterequestData={
      
  "skillInfoModel":[{
                  

    "skill_id":SKILLinfo.skill_id,
          
    "level_id" :SKILLinfo.level_id,
             
   "employee_id" :SKILLinfo.employee_id,
   "created_by" :SKILLinfo.created_by, 
   "update_by" : SKILLinfo.update_by,
   "flag":SKILLinfo.flag,
   "id":SKILLinfo.id
            
  }],
          
      
  "sessionId" :"14",
          
  "transactionType" : "update"
      
  
  }
  

  
      this.hrms.updateSkill(updaterequestData).subscribe(res =>{
           this.updateRes= res;
              
          console.log(this.updateRes);
        
               this.getSkillInfo();
          })
     
    
}
//skill Info ends




  //contact info strats
  contactList:any;
  contactInfolist:any;
  contactinfoReq:any;
  transactionType:any;
  //updateRes:any;
  deletedcontactDetails:any;
  Email:any;
  personal_mobileNo:any;
  current_Address_Line2:any;
  current_Address_Line1:any;
  alternate_mobileNo:any;
  current_City:any;
  current_State:any;
  current_Pin:any;
  permanent_Address_Line_1:any;
  emp_Id:any;
  //created_By:any;
  contactlist:any;
  savecontactres:any;
  //value: boolean;
  contactinfoArr:any;

  contactgetbyid:any;
  contactInfo:any;
  contactInfoDetails:any;
  empinfoarr:any;
// Method for Save Contact Information
    setContactInformation() {
     
      
      var RequestData = {

        "empInfo" :  [{ 
                 "email":this.ContactInfo.email,
                 "personal_mobileNo":this.ContactInfo.personal_mobileNo,
                 "alternate_mobileNo": this.ContactInfo.alternate_mobileNo,
                 "current_Address_Line1": this.ContactInfo.current_Address_Line1,
                 "current_Address_Line2": this.ContactInfo.current_Address_Line2,
                 "current_City": this.ContactInfo.current_City,
                "current_State": this.ContactInfo.current_State,
                 "current_Pin": this.ContactInfo.current_Pin,
                 "permanent_Address_Line_1":this.ContactInfo.permanent_Address_Line_1,
                 "emp_Id": this.ContactInfo.emp_Id,
                 "created_By":"Ojas12"
             }],
       "transactionType" : "save"
     }
this.hrms.setContactInfo(RequestData).subscribe(responce=>{
  this.contactinfoReq = responce;
  console.log(this.contactinfoReq);
   if(this.contactinfoReq.statusMessage == "employee contact info saved successfully"){
     //this.value = false;
   // swal(this.contactinfoReq.statusMessage, "","success");
     this.getContactInformation();
   }
  this.contactinfoArr = this.contactinfoReq.empInfo;
  this.getContactInformation();
})
  
    }
//method for get contact info
    getContactInformation() {
      var request = {
        "transactionType" : "getAll" 
      }
      this.hrms.getContactInfo(request).subscribe(res =>{
     this.contactInfolist = res;
     this.contactList = this.contactInfolist.empContacts;
     console.log(this.contactList);
     
      })
    }

    valueAdd(){
      this.isUpdate=false;
      this.createdby= true;
    }

     //isUpdate = true;
     createdby= false;

     public ContactInfo= {
       "id":"",
       "email": "",
       "personal_mobileNo":"",
       "alternate_mobileNo": "",
       "current_Address_Line1":"",
       "current_Address_Line2": "",
       "current_City":"",
       "current_State": "",
       "current_Pin":"",
       "permanent_Address_Line_1": "",
       "emp_Id": "",
       "created_By": "",
       "updated_By": "",
       "created_date":"",
       "updated_date":"",
       "flag":""
     }
    
    //method for edit  contact Info
       getdatabyId(Cilist){
         this.isUpdate = true;
    
        this.createdby = false;

         console.log(Cilist);
    
    
     var cid= Cilist.id;
     var getupdatedata = {
      
    
         "transactionType" : "getAll",
        "empInfo" : [{
                   "id" : cid
                     }]
        
      
    }
    
    this.hrms.getcontactbyId(getupdatedata).subscribe(res =>{
      this.contactgetbyid=res;
      this.contactInfoDetails=this.contactgetbyid.empContacts;
      this.ContactInfo=this.contactInfoDetails[0];
      console.log("this.ContactInfo",this.ContactInfo)
    })
    
  }
  //method for update contantInfo

updateContactdetails(){

  var updateRequestData={
    

      "empInfo" : [{
               "email" : this.ContactInfo.email ,
              "personal_mobileNo" : this.ContactInfo.personal_mobileNo ,
              "alternate_mobileNo" : this.ContactInfo.alternate_mobileNo,
              "current_Address_Line1" : this.ContactInfo.current_Address_Line1,
              "current_Address_Line2" :  this.ContactInfo.current_Address_Line2,
              "current_City" : this.ContactInfo.current_City,
        
        "current_Pin" :  this.ContactInfo.current_Pin,
        "current_State" : this.ContactInfo.current_State ,
        "permanent_Address_Line_1" : this.ContactInfo.permanent_Address_Line_1 ,
        "emp_Id" : this.ContactInfo.emp_Id ,
        "id" : this.ContactInfo.id,
        "updated_By" :"ojas456"
      }],
         
        "transactionType" : "update"
               
      }
        this.hrms.updateContactInfo(updateRequestData).subscribe(res =>{
             this.updateRes = res;
                
            console.log(this.updateRes);
                 this.getContactInformation();
            })
       
      
  }

 //method for delete contactInfo

    deleteContactInformation(Cilist) {
      var deleteReq = {
 
        "empInfo" : [{
         "id" :Cilist.id,
         "updated_By" : "ojas35"
        }
      ],
         
         
         "transactionType" : "delete"
         
       }
    this.hrms.deleteContactInfo(deleteReq).subscribe(res =>{
    this.deletedcontactDetails = res;
    this.empinfoarr = this.deletedcontactDetails.empInfo;
    console.log(this.deletedcontactDetails);
    this.getContactInformation();
    })
    }
//contacytInfo ends

//certtification starts
    certificationName:any;
    issuedBy:any;
    dateOfIssue:any;
    // employeeId:any;
    // updatedBy:any;
    // flag:boolean;
    // createdBy:any;
    certificationReq:any;
    certificationArr:any;
    certificationDetailslist:any;
    cerDetailsList:any;
    deletedcertificationDetails:any;
    certificationDetailsListarr:any;
    CertificationDetails:any;
    certificationbyid:any;
  //  id:any;
    updateRequest:any;


    //method for save certification details
    setCertificationDetails(){

      var requestData = {
  
       
          "certificationDetailsModel":[
           {
                         
          "certificationName":this.Certification.certificationName,
          "issuedBy":this.Certification.issuedBy,
          "dateOfIssue":this.Certification.dateOfIssue,
          "createdBy":this.Certification.createdBy,
          "employeeId":this.Certification.employeeId,
          "flag":this.Certification.flag
           }
          ],
                
           "sessionId":"fk21",
           "transactionType": "save"
          }
          

          this.hrms.setCertification(requestData).subscribe(responce=>{
            this.certificationReq = responce;
            console.log(this.certificationReq );

         
            if(this.certificationReq.statusMessage == "Employee Certification detail saved successfuly"){
              //this.value = false;
             //swal(this.certificationReq.statusMessage, "","success");
              this.getCertificationDetails();
            }
            this.certificationArr = this.certificationReq.certificationDetailsModel;
            this.getCertificationDetails();
           
          });
        }


        //method for get cerfification details
    getCertificationDetails(){

            var request =  {
       
              "certificationDetailsModel":[
               {
              
               
               }],
                    
               "sessionId":"fk21",
               "transactionType": "get"
              }
            this.hrms.getCertification(request).subscribe(res =>{
           this.certificationDetailslist = res;
           this.cerDetailsList = this.certificationDetailslist.certificationDetailsList;
           console.log(this.cerDetailsList);
           
            })
          }

//method for delete certification
          deleteCertificationDetails(certifications){
            var Deletereq =  {
       
              "certificationDetailsModel":[
               {
              "id":certifications.id,
              "updatedBy":certifications.updatedBy
               
               }],
                    
               "sessionId":"fk21",
               "transactionType": "delete"
              }
              this.hrms.deleteCertification(Deletereq).subscribe(res =>{
                this.deletedcertificationDetails = res;
                this.certificationDetailsListarr = this.deletedcertificationDetails.certificationDetailsList;
                console.log(this.deletedcertificationDetails);
               this.getCertificationDetails();
                });
          }


Addvalue(){
  this.isUpdateBy=false;
  this.createdBY=true;
}
isUpdateBy=false;
createdBY=true;

public Certification = {
  "certificationName":"",
  "issuedBy":"",
  "dateOfIssue":"",
"id":"",
  "createdBy":"",
  "updatedBy":"",
  "employeeId":"",
  "flag":""
  
}

//edit certification details
getDatabyId(certifications){
  this.isUpdateBy=true;
  this.createdBY=false;
  console.log(certifications);
   var Cerid= certifications.id;
  
  var getUpdatedata =  {
       
    "certificationDetailsModel":[
     {
    "id":Cerid
   }
  ],
          
     "sessionId":"fk21",
     "transactionType": "getbyid"
    }
    
 
 this.hrms.getCertificationbyId(getUpdatedata).subscribe(res =>{
   this.certificationbyid=res;
   this.CertificationDetails=this.certificationbyid.certificationDetailsList;
   this.Certification=this.CertificationDetails[0];
   console.log("this.Certification",this.Certification)
 })
}

//method for update certification details
updateCertificationdetails(){

  var updaterequestData= {
       
    "certificationDetailsModel":[
     {
                   
    "certificationName":this.Certification.certificationName,
    "issuedBy":this.Certification.issuedBy,
    "dateOfIssue":this.Certification.dateOfIssue,
    "id":this.Certification.id,
    "employeeId":this.Certification.employeeId,
    "updatedBy":this.Certification.updatedBy,
    "createdBy":this.Certification.createdBy,
    "flag":this.Certification.flag
     }],
          
     "sessionId":"fk21",
     "transactionType": "update"
    }
    
        this.hrms.updateCertification(updaterequestData).subscribe(res =>{
             this.updateRequest = res;
                
            console.log(this.updateRequest);
                 this.getCertificationDetails();
            })
       
      
  }
//certificatiion details ends



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

  addbusinessUnit(){
    this.isUpdate = false;
    
    this.createdby1 = true;
  }
public businessUnitObj=
{
  "employeeId": "",
  "sbu": "",
  "flag": "",
  "createddate": "",
  "updateddate": "",
  "createdby": "",
  "updatedby": "",
  "id": "",
  "status": "Active"
}





getbusines(){
  var businesinfo = {
    "employeeBUDeatils": [{

    }],
      "transactionType" :"getall"
}
   this.hrms.getBusinessunit(businesinfo).subscribe(res =>{
  this.empbu =res;
  this.empbuinfo= this.empbu.listCourse;
   console.log(this.empbu);
   })
  
}
savebusinesunit(){
  var savebusines={
    "employeeBUDeatils": [{
            "employeeId" : this.businessUnitObj.id,
            "sbu" : this.businessUnitObj.sbu,
            "status" : this.businessUnitObj.status,
            "createdby" : this.businessUnitObj.createdby,
            "flag" : this.businessUnitObj.flag
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

    this.getbusines();

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

       this.getbusines();
     }
  })
  
  }
  updatebusinesunitint(){
 
    var updatebusinesunitkt={
      "employeeBUDeatils": [{
              //"id" : this.businessUnitObj.id,
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
         if(this.updateRes.statusMessage == "Successfully Education record updated"){
           swal(this.updateRes.statusMessage, "","success");
          this.getdependentData();
         
    this.getbusines();
         }
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
 // emp basic starts //


 emp_fname:any;
 emp_mname:any;
 emp_lname:any;
 emp_dob:any;
 emp_status:any;
 emp_id:any;
 emp_password:any;
 emp_created:any;
 emp_gender:any;
 basicinfo:any;
 delete_data:any;
 emp_basic:any;
 basicInfobyid:any;
 updateempdata:any;
 basicInfoDetails:any;
 basicUnitObj:any;
 created:any;
 CREATEDBY2:any;
 createdby2:any;
 isUpdate1:any;
 
 isUPDATEDBY2:any;

 addempUnit(){
  this.isUpdate = false;
  
  this.createdby1 = true;
}


 public empinobj=
{
            "id":"",
                "firstname" : "",
                "middlename" : "",
                "lastname" : "",
                "status" : "",
             "dob":"",
                "gender" : "",
                "password" : "",
                "employeeId" : "",
                        "updatedBy" : "",
                        "createdBy":""

}



 getempdata(){
  var empinfo = {
  
    "employeeInfo" :[{
         

    }],
    "transactionType" : "getall"

    
} 
   this.hrms.getempinfo(empinfo).subscribe(res =>{
  this.empbasic =res;
  this.empbasicinfo= this.empbasic.employeeInfo;

   console.log(this.empbasicinfo);
   })
  
}

saveemployeeInfo(){
  var request={
    "employeeInfo" :[{
         
           
                   
                    "firstname" : this.empinobj.firstname,
                    "middlename" : this.empinobj.middlename,
                    "lastname" : this.empinobj.lastname,
                    "status" : this.empinobj.status,
                 "dob":this.empinobj.dob,
                    "gender" : this.empinobj.gender,
                    "password" : this.empinobj.password,
                    "createdBy":this.empinobj.createdBy,
                    "employeeId" : this.empinobj.employeeId,
                  
    }],
    "transactionType" : "save"
  }
  this.hrms.saveempinfo(request).subscribe(res =>{
    this.basicinfo =res;
    //this.businesdetails= this.businesinfo.listCourse;

    console.log(this.basicinfo);
  if(this.basicinfo.statusMessage == "Success fully record added"){
          swal(this.basicinfo.statusMessage, "","success");
  
      this.getempdata();
  }
     })
    
  }


  deleteemp(empbasic){
    var reguest1={
      "employeeInfo" :[{
             "id":empbasic.id

      }],
      "transactionType" : "delete"

    }
      this.hrms.deleteempinfo(reguest1).subscribe(response=>{
        this.delete_data=response;
        console.log(this.delete_data);
        if(this.delete_data.statusMessage == "Success fully record deleted"){
           swal(this.delete_data.statusMessage, "","success");
          
           this.getempdata();
          }
      })
      
      }
      updateempinfo(){
       
       


        var updateempinfo={
          "employeeInfo" :[{
                 "id":this.empinobj.id,
                  "firstname" : this.empinobj.firstname,
                  "middlename" : this.empinobj.middlename,
                  "lastname" : this.empinobj.lastname,
                  "status" : this.empinobj.status,
               "dob":this.empinobj.dob,
                  "gender" : this.empinobj.gender,
                  "password" : this.empinobj.password,
                  "employeeId" : this.empinobj.employeeId,
                      
          }],
          "transactionType" : "update"
  
          
  }
        this.hrms.updateempinfo(updateempinfo).subscribe(res =>{
          this.updateempdata = res;
          console.log(this.updateempdata);
           if(this.updateempdata.statusMessage == "Success fully record updated")
           {
              swal(this.updateempdata.statusMessage, "","success");
            //   this.getdependentData();
             
        this.getempdata();
           }
        })
      
        
      
       }

       getbyIdbasicdata(empbasic){

        this.createdby1 = false;
        this.isUpdate = true;
    
    
        var empdataid= empbasic.id;
       
        
        var GetUpdatebasicInfo ={
          "employeeInfo" :[{
                 "id":empdataid
                 
                 
          }],
          "transactionType" : "getById"
  
          
  }
        
        
        this.hrms.getbyIdempinfo(GetUpdatebasicInfo).subscribe(res =>{
         this.basicInfobyid=res;
         this.basicInfoDetails=this.basicInfobyid.employeeInfo;
         
         this.empinobj=this.basicInfoDetails[0];
         console.log("this.basicUnitObj",this.empinobj);
        })
        }
    

        // ----Employee Experience Details Starts---------------------------------------------
empexp : any;
empexpdetails :any;
saveRes :any;

//---getting employee details---------------------------------------------------
getEmpExp()
{
  var requestObj = 
  {
    "employeeExperienceDetails" :[ {

            
    }],
             "transactionType":"getall",
            "sessionId" : "any String" 
  }

  this.hrms.getEmployeeExperienceDetails(requestObj).subscribe(response =>{
    this.empexp = response;
    this.empexpdetails = this.empexp.employeeExperienceDetails;
    console.log(this.empexp);

  })
}

//---adding employee experience----------------------------------------------------

company_name:any;
joining_date:any;
exit_date:any;
salary:any;
//location:any;
is_current_company:boolean;
//employee_Id:any;
first_Reference_Name:any;
first_Reference_Contact:any;
second_Reference_Name:any;
second_Reference_Contact:any;
sessionId:any;


isCreated:boolean=false;



EmpExpArr:any;

  saveEmpExp(){
    var savReqObj =
    
      {
        "employeeExperienceDetails" : 
        
        [ 
          {
                 "company_name":this.editemparrlist.company_name,
                 "joining_date":this.editemparrlist.joining_date,
                 "exit_date":this.editemparrlist.exit_date,
                 "salary":this.editemparrlist.salary,
                 "location":this.editemparrlist.location,
                 "is_current_company":this.editemparrlist.is_current_company,
                 "employee_Id":this.editemparrlist.employee_Id,
                 "first_Reference_Name":this.editemparrlist.first_Reference_Name,
                 "first_Reference_Contact":this.editemparrlist.first_Reference_Contact,
                 "second_Reference_Name":this.editemparrlist.second_Reference_Name,
                 "second_Reference_Contact":this.editemparrlist.second_Reference_Contact,
                 "created_by":this.editemparrlist.created_by,
                 
                
          }
        ],
        
            "transactionType" : "save",
             "sessionId" : "any String" 
    }

    this.hrms.saveEmployeeExperienceDetails(savReqObj).subscribe(res =>{
      this.saveRes = res;
      console.log(this.saveRes);
      this.EmpExpArr = this.saveRes.employeeExperienceDetails;
      console.log(this.EmpExpArr);
      this.getEmpExp();
    })

  }

//---deleting employee experience---------------------------
deleteres:any;
deleteEmpExpArr:any;


deleteEmpExp(emp)
{
var deleteReqObj =
{
"employeeExperienceDetails" :
[{
"id":emp.id,
 "updated_by":emp.updated_by
}],
  "transactionType":"delete",
  "sessionId" : "any String" 
}
this.hrms.deleteEmployeeExperienceDetails(deleteReqObj).subscribe(res => {
this.deleteres = res;

this.deleteEmpExpArr = this.deleteres.employeeExperienceDetails;
console.log(this.deleteres);

this.getEmpExp();
})
}

//---editing employee experience------------------------------------------
editemp:any;
editemparr:any;

public editemparrlist = 
{
           "id":"",
            "company_name":"",
            "salary":"",
            "location":"",
            "joining_date":"",
            "exit_date":"",
            "is_current_company":"",
            "employee_Id":"",
            "first_Reference_Name":"",
            "first_Reference_Contact":"",
            "second_Reference_Name":"",
            "second_Reference_Contact":"",
            "updated_by":"",
            "created_by":"",
          
}


addempexp()
{
this.isUpdate = false;
this.isCreated = true;

}

editempexpbyid(emp)
{

this.isUpdate = true;
this.isCreated = false;
var empid = emp.id;
var editempexpobj = 
{
"employeeExperienceDetails" :[ {
  "id":empid


  
}],
   "transactionType":"getById",
  "sessionId" : "any String" 
}

this.hrms.editEmpExpDetails(editempexpobj).subscribe(res => {
this.editemp = res;
this.editemparr = this.editemp.employeeExperienceDetails;
this.editemparrlist = this.editemparr[0];
console.log("this.editemparrlist",this.editemparrlist);

})
}



//---updating employee experience------------------------

 updateres:any;
 updateEmpExpArr:any;
 newUserFormExp:any;
 


 updateEmpExp(emp)
 {
   
  var updateReqObj =
  {
  "employeeExperienceDetails" :
  [{
    
          "id":this.editemparrlist.id,
          "company_name":this.editemparrlist.company_name,
          "salary":this.editemparrlist.salary,
          "location":this.editemparrlist.location,
          "joining_date":this.editemparrlist.joining_date,
          "exit_date":this.editemparrlist.exit_date,
          "is_current_company":this.editemparrlist.is_current_company,
          "employee_Id":this.editemparrlist.employee_Id,
          "first_Reference_Name":this.editemparrlist.first_Reference_Name,
          "first_Reference_Contact":this.editemparrlist.first_Reference_Contact,
          "second_Reference_Name":this.editemparrlist.second_Reference_Name,
          "second_Reference_Contact":this.editemparrlist.second_Reference_Contact,
          "updated_by":this.editemparrlist.updated_by,
        
          
  }],
            "transactionType":"update",
           "sessionId" : "any String" 
 
      
}
this.hrms.updateEmployeeExperienceDetails(updateReqObj).subscribe(res => {
  this.updateres = res;
  console.log(this.updateres);
  this.updateEmpExpArr = this.updateres.employeeExperienceDetails;
  console.log(this.updateEmpExpArr);
  this.getEmpExp();
})

}





//---Employee KYE details starts--------------

//--- getting Employee KYE details-------------------
empkye:any;
empkyearr:any;
getEmpKye()
{
var reqObj = 
{
"kye" :[{

}],
        "transactionType"     :  "getAll"
}

this.hrms.getEmployeeKyeDetails(reqObj).subscribe(res =>{
this.empkye = res;
console.log(this.empkye);
this.empkyearr = this.empkye.kyeList;
})
}


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
this.savekyeresarr = this.savekyeres.kye;

//   if(this.savekyeres.statusMessage == "Success fully record added")
// {
//    this.value =false;
//    swal(this.savekyeres.statusMessage,"","success");
//    this.getEmpKye();
// }
this.getEmpKye();
})

}

//--- deleting Employee KYE details-------------------
deleteKye;any;
deleteKyearr:any;

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
this.getEmpKye();
})
}

//--- Updating Employee KYE details-------------------
isUpdate:boolean=false;
editkye:any;
editkyearr:any;
kye:any;

addeditkye()
{
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
this.getEmpKye();
})
}


//---- KYE details  Ends----------------------------------------------


//----Employee Title Details Starts---------------

//----Getting Title Details--------------------
gettitle:any;
gettitlearr:any;

getempTiltle()
{
var reqtitle = 
{
"model": [{
    
}],
  "transactionType" :"getall"
}

this.hrms.getEmpTitleDetails(reqtitle).subscribe(res =>{
this.gettitle = res;
this.gettitlearr = this.gettitle.listCourse;
console.log(this.gettitlearr);
})
}

//--Saving employee title ----------------

isCreatedby:boolean=false;
isUpdatedby:boolean=false;
saveEmpObj:any;
savetitlearr:any;
role:any;
title:any;
//employeeId:any;
//createdby:any;
//flag:boolean;


clickadd()
{
this.isCreatedby=true;
this.isUpdatedby=false;
}

saveempTitle()

{
  var saveempobj = 
  {
    "model": [{
      "role": this.edittitle.role,
      "title" : this.edittitle.title,
      "employeeId" : this.edittitle.employeeId,
      "createdby" : this.edittitle.createdby,
       //"flag" : this.edittitle.flag
}],
"transactionType" :"save"
  }
  this.hrms.saveEmpTitleDetails(saveempobj).subscribe(res => {
  this.saveEmpObj = res;
  this.savetitlearr = this.saveEmpObj.model;
  console.log(this.savetitlearr);
  this.getempTiltle();
})

}

//--Deleting employee title ----------------

deletetitle:any;
deletetitlearr:any;
deleteempTitle(title)
{
var deleteReqtitle = 
{
  "model": [{
    "id" : title.id
}],
"transactionType" :"delete"
}

this.hrms.deleteEmpTitleDetails(deleteReqtitle).subscribe (res => {
  this.deletetitle = res;
  console.log(this.deletetitle);
  this.deletetitlearr = this.deletetitle.listCourse;
  this.getempTiltle();
})
}


//----Editing Employee title-----------------------

public edittitle = 
{
"id" : "",
"role": "",
"title" : "",
"employeeId" : "",
"updatedby" : "",
"createdby":"",
"flag":""
}

editTitle:any;
editTitlearr:any;
emptitledetails:any;
updatedby:any;

editempTitleid(title)
{
this.isUpdatedby = true;
this.isCreatedby = false;

var titleid = title.id;
var editempTitleobj = 
{

  "model": [{
         "id": titleid
  }],
    "transactionType" :"getbyid"

}

this.hrms.editEmpTitleDetails(editempTitleobj ).subscribe(res => {
this.editTitle = res;
this.editTitlearr = this.editTitle.listCourse;
this.edittitle = this.editTitlearr[0];
console.log("this.emptitleEdit",this.edittitle);

})
}

//----Updating Employee title-----------------------
updatetitlearr:any;
updateempTitle(title)
{
var updatetileObj = 
{
"model": [{
  "id" : this.edittitle.id,
  "role": this.edittitle.role,
  "title" : this.edittitle.title,
  //"employeeId" : this.edittitle.employeeId ,
  "updatedby" : this.edittitle.updatedby
}],
"transactionType" :"update"
}
this.hrms.updateEmpTitleDetails( updatetileObj).subscribe(updaterestitle =>{
this.updateres = updaterestitle;
console.log(this.updateres);
this.updatetitlearr = this.updateres.model;
this.getempTiltle();
})
}



//----Employee Title Ends-------------------



//----Getting Employee Education Details--------------------
getempedu:any;
getempeduarr:any;

getEmpEdu()
{
var reqtitle = 
{
"employeeEducationDetailsList" :[],

"transaactionType":"getAll",
"pageNo" : 2,
"pageSize" : 2
}

this.hrms.getEmpEduDetails(reqtitle).subscribe(res =>{
this.getempedu = res;
this.getempeduarr = this.getempedu.employeeEducationDetailsList;
console.log("Employee Education get");
console.log(this.getempedu);
})
}

//--Saving Employee Education  Details----------------

isCreatedEduby:boolean=false;
isUpdatedEduby:boolean=false;
saveEmpEduObj:any;
saveEmpEduarr:any;

employeeIdEdu: any;
qualification:any;
year_of_passing :any;
percentage_marks: any;
institution_name: any;
flagEdu: boolean;
createdByEdu:any;
updatedByEdu: any;
createdDate: any;
updatedDate: any;

clickaddEdu()
{
this.isCreatedEduby=true;
this.isUpdatedEduby=false;
}

saveEmpEdu()

{
   var saveempeduobj = 
  {
   "employeeEducationDetailsList" :[
{
      
"employeeId": this.editEduEmp.employeeId,
"qualification": this.editEduEmp.qualification,
"year_of_passing": this.editEduEmp.year_of_passing,
"percentage_marks": this.editEduEmp.percentage_marks,
"institution_name": this.editEduEmp.institution_name,
"flag":this.editEduEmp.flagEdu,
"createdBy": this.editEduEmp.createdbyEdu,
"updatedBy": this.editEduEmp.updatedbyEdu,
"createdDate": this.editEduEmp.createdDate,
"updatedDate": this.editEduEmp.updatedDate,
"id":this.editEduEmp.id
}
],

"transaactionType":"save",
"pageNo" : 2,
"pageSize" : 2

}
this.hrms.saveEmpEduDetails(saveempeduobj).subscribe(res => {
this.saveEmpEduObj = res;
this.saveEmpEduarr = this.saveEmpEduObj.employeeEducationDetailsList;
console.log(this.saveEmpEduObj);
this.getEmpEdu();
})

}


//--Deleting Employee Education Details ----------------

deleteEmpObj:any;
deleteEmpEduarr:any;
deleteEmpEdu(edu)
{
var deleteEmpEduObj = 
{
  
  "employeeEducationDetailsList" :[
    {
        "id": edu.id
       
    }
    ],
    
    "transaactionType":"delete",
    "pageNo" : 2,
    "pageSize" : 2
}


  this.hrms.deleteEmpEduDetails(deleteEmpEduObj).subscribe (res => {
  this.deleteEmpObj = res;
  console.log(this.deleteEmpObj);
  this.deleteEmpEduarr = this.deleteEmpObj.employeeEducationDetailsList;
  this.getEmpEdu();
})
}


// //----Editing Employee Education  Details-----------------------

public editEduEmp = 
{
"id" : "",
"employeeId" : "",
"qualification": "",
"year_of_passing":"",
"percentage_marks":"",
"institution_name":"",
"flagEdu":"",
"updatedbyEdu" : "",
"createdbyEdu":"",
"createdDate":"",
"updatedDate":""
}

editEmpEdu:any;
editEmpEduarr:any;
empEmpEdudetails:any;
updatedEduby:any;

editEmpEduId(edu)
{
this.isUpdatedEduby = true;
this.isCreatedEduby = false;

var eduid = edu.id;
var editEmpEduobj = 
{

    "employeeEducationDetailsList" : [{
   "id":eduid

 }],

"transaactionType":"getall",
"pageNo" : 2,
"pageSize" : 2
}

this.hrms.editEmpEduDetails(editEmpEduobj).subscribe(res => {
this.editEmpEdu = res;
this.editEmpEduarr = this.editEmpEdu.employeeEducationDetailsList;
this.editEduEmp = this.editEmpEduarr[0];
console.log("this.empEmpEdudetails",this.editEduEmp);

})
}

//----Updating Employee Education details-----------------------
updateedures:any;
updateEduarr:any;

updateEdu()
{
var updateEduObj = 
{
"employeeEducationDetailsList" :[
  {
 
    "employeeId": this.editEduEmp.employeeId,
    "qualification": this.editEduEmp.qualification,
    "year_of_passing": this.editEduEmp.year_of_passing,
    "percentage_marks": this.editEduEmp.percentage_marks,
    "institution_name": this.editEduEmp.institution_name,
    "flag":true,
    "updatedBy": this.editEduEmp.updatedbyEdu,
    "id":this.editEduEmp.id
}
],

"transaactionType":"update",
"pageNo" : 2,
"pageSize" : 2
}



this.hrms.updateEmpEduDetails(updateEduObj).subscribe(updateEdu =>{
 this.updateedures = updateEdu;
 console.log(this.updateedures);
 this.updateEduarr = this.updateedures.employeeEducationDetailsList;
 this.getEmpEdu();
})
}

//----Employee Education details Ends--------------------
//dependent starts

//dependent details starts
OnSave()
{
  
 this.createdByDependent=true;
  this.isupdateDependent=false;
}

// getdependentData(){
//   var requestData ={
//     "dependentDetails" : [
//                                             {
                                              
//                                             }
//                    ], 
//                    "transactionType":"getall",
//                   "sessionId" : "any String" 
//   }

//     this.hrms.getdependentdetails(requestData).subscribe(response =>{
//       this.dependent_details = response;
//       this.dependents = this.dependent_details.getDependentDetailsList;

//       console.log(this.dependent_details);
    
//     })
//   }



  onsavedependentdata(){

    var requestData = {
      "dependentDetails" : [
                                    {
                                    "dependent_Name":this.dependentdetailss.dependent_Name,
                                    "relation":this.dependentdetailss.relation,
                                    "date_Of_Birth":this.dependentdetailss.date_Of_Birth,
                                    "employee_Id":this.dependentdetailss.employee_Id,
                                   
                                     "created_By":this.dependentdetailss.created_By

                         }
                           ], 
                     "transactionType":"save",
                    "sessionId" : "any String" 
    }
    
    this.hrms.savedependentdetails(requestData).subscribe(response =>{
   this.dependent_details = response;
       
       if(this.dependent_details.statusMessage == "DependentDetails have saved successfully"){
       // swal(this.dependent_details.statusMessage, "","success");
        this.getdependentData();
      }
       
    })
    
 }
 createdByDependent= false;

 isupdateDependent = false;
 public dependentdetailss={
  
          
            "id":"",
            "dependent_Name":"",
            "relation":"",
            "date_Of_Birth":"",
            "employee_Id":"",
            "updated_By":"",
            "created_By":""

 }

//  updatedependentdata(){
 
//   var updatedependentdata ={
//     "dependentDetails" : [
//                                             {
//                                                                     "id":this.dependentdetailss.id,
//                                   "dependent_Name":this.dependentdetailss.dependent_Name,
//                                   "relation":this.dependentdetailss.relation,
//                                   "date_Of_Birth":this.dependentdetailss.date_Of_Birth,
//                                   "employee_Id":this.dependentdetailss.employee_Id,
//                                   "updated_By":this.dependentdetailss.updated_By
//                        }
                       
                      
//                    ], 
//                    "transactionType":"update",
//                   "sessionId" : "any String" 
//   }
  
//   this.hrms.updatedependentdetails(updatedependentdata).subscribe(res =>{
//     this.updateRes = res;
//     console.log(this.updateRes);
//       if(this.updateRes.statusMessage == "DependentDetails are updated successfully"){
//         swal(this.updateRes.statusMessage, "","success");
//         this.getdependentData();
        
        
//       }
//   })

// }
//  deletedependentdata(dependent){

//   var deletedependentdata ={
//     "dependentDetails" : [
//                       {
//                            "id":dependent.id,
//                            "updated_By":dependent.updated_By
//                        },
                       
                      
//                    ], 
//                    "transactionType":"delete",
//                   "sessionId" : "any String" 
//   }
// this.hrms.deletedependentdetails(deletedependentdata).subscribe(res=>{
//   this.deleteRes=res;
//   if(this.deleteRes.statusMessage == "DependentDetails are deleted successfully"){
//     swal(this.deleteRes.statusMessage, "","success");
//     this.getdependentData();
//   }

// })
  
//  }




 getdependentdetailsById(dependent){
  this.isupdateDependent = true;
  this.createdByDependent = false;
   var ddid = dependent.id;
   var dependentdetailsByid={
    "dependentDetails" : [
                                            {
                                                    "id": ddid
                       }
                   ], 
                   "transactionType":"getbyid",
                  "sessionId" : "any String" 
  }
  this.hrms.getdependentdetails(dependentdetailsByid).subscribe(response =>{
    this.  employee_dependentdetailsById = response;
    this. dependent_dtdetails = this.employee_dependentdetailsById.getDependentDetailsList;
    this.dependentdetailss = this.dependent_dtdetails[0]
      
  
  })

 }
//dependent detils ends
//bank details starts 


public bankdetailss ={
  "id":"",
  "bank_account_no":"",
  "bank_name"   :"",
  "bank_city"     :"",
  "bank_branch"    :"",
  "bank_ifsc_code":"",
  "bank_account_status":"",
  "employee_id":"",
  "is_active"  :"",
  "created_by":"",
  "updated_by":""
}


//  getbankdetails(){
//  var bankdetails={
//   "bankDetails":[{
         
  
// }],
// "transactionType":"getall"

// }
// this.hrms.getbankserverdetails(bankdetails).subscribe(response =>{
//   this.employee_bankdetails = response;
//   this.bankdt = this.employee_bankdetails.listBankDetails;
// console.log(this.bankdt);

// })
// }

getbankdetailsbyId(bank){
  
  this.isupdateDependent = true;
  this.createdByDependent = false;
  var bbid = bank.id;
  var bankdetailsByid={
    "bankDetails":[{
            "id":bbid
    
}],
"transactionType":"getall"

}
 this.hrms.getbankserverdetails(bankdetailsByid).subscribe(response =>{
   this.employee_bankdetailsById = response;
   this.bank_details = this.employee_bankdetailsById.listBankDetails;
   this.bankdetailss = this.bank_details[0]
 console.log("this.bankdetailss",this.bankdetailss);
 
 })
 }


Onsavebank(){
  var user  ="user";
  
    var savebank = 
  
    {
      "bankDetails":[{
              "bank_account_no":this.bankdetailss.bank_account_no,
              "bank_name"   :this.bankdetailss.bank_name,
              "bank_city"     :this.bankdetailss.bank_city,
              "bank_branch"    :this.bankdetailss.bank_branch,
              "bank_ifsc_code":this.bankdetailss.bank_ifsc_code,
              "bank_account_status":this.bankdetailss.bank_account_status,
              "employee_id":this.bankdetailss.employee_id,
              "is_active"  :this.bankdetailss.is_active,
              "created_by":user
              }
     
      ],
      "transactionType":"save"
  }
  this.hrms.savebankdetails(savebank).subscribe(response =>{
    this.employee_bankdetails = response;
          console.log(this.employee_bankdetails);

         this.getbankdetails();
         if(this.employee_bankdetails.statusMessage == "BankDetails record is saved Successfully"){
          swal(this.employee_bankdetails.statusMessage, "","success");
          this.getbankdetails();
        }
     })
}
deletebanktddetails(bank){
  var delebank= {
    "bankDetails":[{
            "id"  :bank.id
    }
    
    ],
    "transactionType":"delete"
}
this.hrms.savebankdetails(delebank).subscribe(res=>{
  this.deleteRes=res;
  if(this.deleteRes.statusMessage == "BankDetails record is deleted Successfully"){
   swal(this.deleteRes.statusMessage, "","success");
    this.getbankdetails();
  }

})
}
updatebankdetails(){
  var user="user";
  var updatebankdetails= {
    "bankDetails":[{
            "bank_account_no"    :this.bankdetailss.bank_account_no,
            "bank_name"          :this.bankdetailss.bank_name,
            "bank_city"          :this.bankdetailss.bank_city,
            "bank_branch"        :this.bankdetailss.bank_branch,
            "bank_ifsc_code":this.bankdetailss.bank_ifsc_code,
            "bank_account_status":this.bankdetailss.bank_account_status,
            "employee_id"        :this.bankdetailss.employee_id,
            "is_active"          :this.bankdetailss.is_active,
            "created_by":user
            
   
    }

    ],
    "transactionType":"update"
}
this.hrms.updatebankdetails(updatebankdetails).subscribe(res =>{
  this.updateRes = res;
  console.log(this.updateRes);
    if(this.updateRes.statusMessage == "BankDetails record is updated Successfully"){
      swal(this.updateRes.statusMessage, "","success");
      this.getbankdetails();
    }
})

}
//bank details ends

//employment details starts

getemploymentdetails(){
  var employmentdetailss={
    "employmentDetails" :[ {
                  }
              
               ],
          
          "transactionType":"getAll"
  }
   
 this.hrms.getonboardingdetails(employmentdetailss).subscribe(response =>{
   this.employmentdetailsss = response;
   this.onboarding = this.employmentdetailsss.employmentDetailsList;
 console.log(this.onboarding);
 
 })
 }

 saveemploymentdetails(){

  
    var saveemploymentdetailss = 
  
    {
      "employmentDetails":[ {
        "employeeId":this. onboarddetailsss.employeeId,
        "joiningDate":this.onboarddetailsss.joiningDate,
        "resourceType":this.onboarddetailsss.resourceType,
        "bondStatus": this.onboarddetailsss.bondStatus,
        "resignationDate":this.onboarddetailsss.resignationDate,
        "exitDate":this.onboarddetailsss.exitDate,
        "separationType":this.onboarddetailsss.separationType,
        "flag":this.onboarddetailsss.flag,
        "createdBy":this.onboarddetailsss.createdBy
    }],
    
    "transactionType":"save"
}
  this.hrms.saveonboardingdetails(saveemploymentdetailss).subscribe(response =>{
    this.onboardingdetails = response;
          console.log(this.onboardingdetails);

  
       if(this.onboardingdetails.statusMessage == "Data is inserted successfully"){
         // swal(this.onboardingdetails.statusMessage, "","success");
          this.onboardingdetails();
         }

     })
     
     this.getemploymentdetails();

}
deleteOnbording(onboard){
var deleteemployee={
  "employmentDetails":[ {
    "id":onboard.id,
   "updatedBy":onboard.updatedBy

    
}],

"transactionType":"delete"
}
this.hrms.deleteOnboardingdetails(deleteemployee).subscribe(res=>{
  this.deleteonboarding=res;
   if(this.deleteonboarding.statusMessage == "Data is deleted successfully"){
    // swal(this.deleteonboarding.statusMessage, "","success");
     this.getemploymentdetails();
   }
 

})

}


getboardingdetailsbyId(onboard){
  this.isupdateDependent = true;
  this.createdByDependent = false;
   var onboardid = onboard.id;
   var boardingdetailsbyid={"employmentDetails":[ {
    "id":onboardid
  

}],

"transactionType":"getAll"
}


  this.hrms.getonboardingdetails(boardingdetailsbyid).subscribe(response =>{
    this.  onboard_details = response;
    this. onboard_details_by_id = this.onboard_details.employmentDetailsList;
    this.onboarddetailsss = this.onboard_details_by_id[0];
    console.log("this.onboarddetailsss",this.onboarddetailsss);
      
  
  })

 }
 updateonboardingdetailss(){
  var updatenboarddetails=  {"employmentDetails":[ {
            "id":this.onboarddetailsss.id,
            "employeeId":this. onboarddetailsss.employeeId,
            "resourceType":this. onboarddetailsss.resourceType,
            "bondStatus": this.onboarddetailsss.bondStatus,
            "resignationDate":this.onboarddetailsss.resignationDate,
            "exitDate":this.onboarddetailsss.exitDate,
            "separationType":this.onboarddetailsss.separationType,
            "flag":this.onboarddetailsss.flag,
            "updatedBy":this.onboarddetailsss.updatedBy

            
        }],
        
        "transactionType":"update"
}
this.hrms. updateonboardingdetails(updatenboarddetails).subscribe(res =>{
  this.onboardRes = res;
  console.log(this.onboardRes);
     if(this.onboardRes.statusMessage == "Data is updated successfully"){
       //swal(this.onboardRes.statusMessage, "","success");
       this.getemploymentdetails();
     }
})
//this.getemploymentdetails();
}


public onboarddetailsss={
  "id":"",
  "employeeId": "",
  "joiningDate": "",
  "resourceType": "",
  "bondStatus": "",
  "resignationDate": "",
  "exitDate": "",
  "separationType": "",
  "flag":"",
  "createdBy": "",
  "updatedBy": ""
}



// ends onboarding details 

//project details starts


getProject(){




  var jsonData = {
   
    "transactionType":"getAll"
}
  this.hrms.getProjectDetails(jsonData).subscribe(response =>{
    this.projectDetails = response;
    this.projectDetailsLi = this. projectDetails.projectDetailsList;
    console.log(this.projectDetailsList);
  })
  
  }
  setProject(){
    var requestObj = {
      "projectDetailsList" : [{
                    "projectName":this.projectDetailss.projectName,
                                    "contractId":this.projectDetailss.contractId,
                                    "rateId":this.projectDetailss.rateId,
                                    "employeeId":this.projectDetailss.employeeId,
                                    "startDate":this.projectDetailss.startDate,
                                    "endDate":this.projectDetailss.endDate,
                                    "billingId":this.projectDetailss.billingId,
                                    "projectTechStack":this.projectDetailss.projectTechStack,
                                    "customer":this.projectDetailss.customer,
                                    "location":this.projectDetailss.location,
                                    "gstApplicable":this.projectDetailss.gstApplicable,
                                    "projectType":this.projectDetailss.projectType,
                                    "projectStatus":this.projectDetailss.projectStatus,
                                    "bdmContact":this.projectDetailss.bdmContact,
                                    "isInternal":this.projectDetailss.isInternal,
                                    "createdBy":this.projectDetailss.createdBy
                                
                                   
                         }], 
                     "transactionType":"save",
                    "sessionId" : "any String" 
    }
    this.hrms.setProjectDetails(requestObj).subscribe(response =>{
      this.projectsave = response;
      this.projectDetailsList = this.projectsave.projectDetailsList;
      console.log(this.projectsave);
      if(this.projectsave.statusMessage == "ProjectDetails has saved successfully"){
        //swal(this.projectsave.statusMessage,"","success");
        this.getProject();
      }
      
    })
  }
  
 // isUpdate = false;
  public projectDetailss = {
    "id":"",
    "projectName":"",
    "contractId":"",
    "rateId":"",
    "employeeId":"",
    "startDate":"",
    "endDate":"",
    "billingId":"",
    "projectTechStack":"",
    "customer":"",
    "location":"",
    "gstApplicable":"",
    "projectType":"",
    "projectStatus":"",
    "bdmContact":"",
    "isInternal":"",
    "createdBy":"",
    "updatedBy":""
  };
  getdatabyId1(user){
    this.isUpdate = true;
  
    console.log(user);
  
   
    var pid = user.id;
    var getupdatedata = {
      "projectDetailsList" : [{
              "id": pid
                   
                         }], 
                     "transactionType":"getAll",
                   
    }
    this.hrms.getprojectbyId(getupdatedata).subscribe(res =>{
      this.projectGetById = res;
      this.projectDetails = this.projectGetById.projectDetailsList;
      this.projectDetailss = this.projectDetails[0];
      console.log("this.projectDetailss",this.projectDetailss);
      
      // var projectDetailss = {};
      //console.log(this.projectGetById);
        //  this.projectDetails.forEach(function (value) {
        //   this.projectDetailss=value;
           
        // });
       
    })
  }
  updateprojdata(){
    var updatereq = {
      "projectDetailsList" : [{
              "id":this.projectDetailss.id,
                   "projectName":this.projectDetailss.projectName,
                                    "contractId":this.projectDetailss.contractId,
                                    "rateId":this.projectDetailss.rateId,
                                    "employeeId":this.projectDetailss.employeeId,
                                    "startDate":this.projectDetailss.startDate,
                                    "endDate":this.projectDetailss.endDate,
                                    "billingId":this.projectDetailss.billingId,
                                    "projectTechStack":this.projectDetailss.projectTechStack,
                                    "customer":this.projectDetailss.customer,
                                    "location":this.projectDetailss.location,
                                    "gstApplicable":this.projectDetailss.gstApplicable,
                                    "projectType":this.projectDetailss.projectType,
                                    "projectStatus":this.projectDetailss.projectStatus,
                                    "bdmContact":this.projectDetailss.bdmContact,
                                    "isInternal":this.projectDetailss.isInternal,
                                    "updatedBy":this.projectDetailss.updatedBy
                         }], 
                     "transactionType":"update",
                    "sessionId" : "any String" 
    }
    this.hrms.updateproject(updatereq).subscribe(response =>{
      this.projectdetailsupdate = response;
      console.log(this.projectdetailsupdate);
      if(this.projectdetailsupdate.statusMessage == "ProjectDetails has updated successfully")
      //swal(this.projectdetailsupdate.statusMessage,"","success")
      this.getProject();
    })
  }
  // valueAdd(){
  //   this.isUpdate = false;
  // }
  deleteproj(user){
  var deletep ={
    "projectDetailsList" : [{
                                            "id":user.id,
                                  "updatedBy":user.updatedBy
                                 
                                  
                      
                       }], 
                   "transactionType":"delete",
                  "sessionId" : "any String" 
  }
  this.hrms.deleteproject(deletep).subscribe(response =>{
    this.projectdelete = response;
    console.log(this.projectdelete);
    if(this.projectdelete.statusMessage == "ProjectDetails has deactivated successfully"){
     // swal(this.projectdelete.statusMessage, "","success");
      this.getProject();
    }
   
  })
  }

  


}
  

