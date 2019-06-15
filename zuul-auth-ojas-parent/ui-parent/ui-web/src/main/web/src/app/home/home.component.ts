import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
declare var $: any;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
username:any;
  role: any=[];
  role0:any=[];
  role1: any=[];
  role2: any=[];
  changerole: string;
  constructor(private authService: AuthService, private routerNavigate: Router) { }

  logoutAction() {
    if (this.authService.logOutAction()) {
      this.routerNavigate.navigate(['login'])
    }
  }
  changeRole(){
    if(this.role=='ROLE_ADMIN')
    {
    this.changerole = localStorage.getItem('Role');

    console.log(this.changerole)
    this.role=this.changerole[0][0]
  }

   else if(this.role=='ROLE_HR')
   {
    this.changerole = localStorage.getItem('Role1');

    console.log(this.changerole)
    this.role=this.changerole[0][1]
   }
   else if(this.role=='ROLE_USER')
   {
    this.changerole = localStorage.getItem('Role2');

    console.log(this.changerole)
    this.role=this.changerole[0][2]
   }
  }

  ngOnInit() {
this.username=localStorage.getItem('UserName');
this.role=localStorage.getItem('Role');
//  this.role0=localStorage.getItem('Role0');
// this.role1=localStorage.getItem('Role1');
//  this.role2=localStorage.getItem('Role2');

    $(document).ready(function () {
      $(".push_menu").click(function () {
        $(".wrapper").toggleClass("active");
      });


      (function () {
        "use strict";

        var treeviewMenu = $('.app-menu');

        // Toggle Sidebar
        $('[data-toggle="sidebar"]').click(function (event) {
          event.preventDefault();
          $('.app').toggleClass('sidenav-toggled');
        });

        // Activate sidebar treeview toggle
        $("[data-toggle='treeview']").click(function (event) {
          event.preventDefault();
          if (!$(this).parent().hasClass('is-expanded')) {
            treeviewMenu.find("[data-toggle='treeview']").parent().removeClass('is-expanded');
          }
          $(this).parent().toggleClass('is-expanded');
        });

        // Set initial active toggle
        $("[data-toggle='treeview.'].is-expanded").parent().toggleClass('is-expanded');

        //Activate bootstrip tooltips
        $("[data-toggle='tooltip']").tooltip();

      })();

    });
  }

}
