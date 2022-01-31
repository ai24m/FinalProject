import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sample-template3',
  templateUrl: './sample-template3.component.html',
  styleUrls: ['./sample-template3.component.css']
})
export class SampleTemplate3Component implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }


// sample for accordion
  items = ['Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 5'];
  expandedIndex = 0;

}
