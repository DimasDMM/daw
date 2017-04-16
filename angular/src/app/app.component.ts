import {Component, OnInit} from '@angular/core';
import {Router, NavigationEnd, NavigationStart} from "@angular/router";

@Component({
  selector: 'app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit() {
    this.router.events.subscribe((val) => {
      if(val instanceof NavigationEnd) {
        console.log("########## ROUTER NAVIGATION ##########");
      }
    });
  }
}
