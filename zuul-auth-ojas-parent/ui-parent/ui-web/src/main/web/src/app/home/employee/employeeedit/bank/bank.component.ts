import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert';
import { HrmsService } from 'src/app/home/services/hrms.service';

@Component({
  selector: 'app-bank',
  templateUrl: './bank.component.html',
  styleUrls: ['./bank.component.scss']
})
export class BankComponent implements OnInit {

  constructor(private hrms:HrmsService) { }

  ngOnInit() {
    this.getbankdetails();
  }
  
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
  updateRes:any;
  isupdateDependent:any;
  createdByDependent:any;
  
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


 getbankdetails(){
 var bankdetails={
  "bankDetails":[{
         
  
}],
"transactionType":"getall"

}
this.hrms.getbankserverdetails(bankdetails).subscribe(response =>{
  this.employee_bankdetails = response;
  this.bankdt = this.employee_bankdetails.listBankDetails;
console.log(this.bankdt);

})
}

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

// Add Bank details Button 

AddBank(newUserFormBank){
  newUserFormBank.reset();
  this.createdByDependent=true;
  this.isupdateDependent=false;
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

}

