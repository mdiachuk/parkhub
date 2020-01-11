import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-page',
  templateUrl: './homePage.component.html',
  styleUrls: ['./homePage.component.scss']
})
export class PageComponent implements OnInit {

  search: string;

  constructor() {
  }

  ngOnInit() {
  }

  addSearch() {
    console.log(this.search)
    //TODO: get value from the form field and set it as search. it should work, maybe :)
  }

  firstSearch(){
    this.search = "";
  }

}
