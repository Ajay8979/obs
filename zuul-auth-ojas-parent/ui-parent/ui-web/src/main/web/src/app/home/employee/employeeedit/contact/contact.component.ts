import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert';
import { HrmsService } from './../../../services/hrms.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  constructor(private hrms:HrmsService) { }

  ngOnInit() {
    this.getContactInformation();
    this.getStatus();
  }
  
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


  StateDetails:any;
  StateList:any;
  getcontactontable:any;
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
                 "created_By":"user"
             }],
       "transactionType" : "save"
     }
this.hrms.setContactInfo(RequestData).subscribe(responce=>{
  this.contactinfoReq = responce;
  console.log(this.contactinfoReq);
  if(this.contactinfoReq.statusMessage == "employee contact info saved successfully"){
    
    swal(this.contactinfoReq.statusMessage, "","success");
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


     for(let i=0;i<=this.contactList.length;i++){

      for(let j=0;j<this.StateList.length;j++){
      if(this.StateList[j].id==this.contactList[i].current_State){
      this.getcontactontable=this.StateList[j].stateName;
      console.log("Qualification details");
      console.log(this.getcontactontable);
      } 
      }
      this.contactList[i].current_State=this.getcontactontable;
      console.log("Final Educational ");
      console.log(this.contactList);
      }

      })
    }

   
   







 
    isUpdate:boolean;
//master get method for stateslist
getStatus() {
  var request =
    {
       
      "states":
       [{
        "stateName":"Hariyana",
        "id":4
       }],
            
      "sessionId":"1234",
       "transactionType": "getall"
       
      }
this.hrms.getStatusList(request).subscribe(res =>{
  this.StateDetails = res;
  this.StateList = this.StateDetails.statesList;
  console.log(this.StateList);
})
}

valueAdd(newUserFormcontact){
  newUserFormcontact.reset()
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
  updateRes:any;
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
        "current_State" :this.ContactInfo.current_State,
        "permanent_Address_Line_1" : this.ContactInfo.permanent_Address_Line_1 ,
        "emp_Id" : this.ContactInfo.emp_Id ,
        "id" : this.ContactInfo.id,
        "updated_By" : "user45"
      }],
         
        "transactionType" : "update"
               
      }
        this.hrms.updateContactInfo(updateRequestData).subscribe(res =>{
             this.updateRes = res;
                
            console.log(this.updateRes);
            if(this.updateRes.statusMessage == "employee contact info updated successfully"){
              swal(this.updateRes.statusMessage, "","success");
              this.getContactInformation();
            }
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
    if(this.deletedcontactDetails.statusMessage == "employee contact info deleted successfully"){
      swal(this.deletedcontactDetails.statusMessage, "","success");
      this.getContactInformation();
    }
    this.getContactInformation();
    })
    }
//contacytInfo ends

}
