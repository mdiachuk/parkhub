import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-page',
  templateUrl: './homePage.component.html',
  styleUrls: ['./homePage.component.scss']
})
export class PageComponent implements OnInit {

  childSearch: string;
  search: string;

  constructor() {
  }

  ngOnInit() {
  }

  addSearch() {
    this.childSearch = this.search;
  }

}
