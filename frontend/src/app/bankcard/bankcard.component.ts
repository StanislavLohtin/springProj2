import {Component, Input, OnInit} from '@angular/core';
import {Bank} from '../bank';

@Component({
  selector: 'app-bankcard',
  templateUrl: './bankcard.component.html',
  styleUrls: ['./bankcard.component.css']
})
export class BankcardComponent implements OnInit {
  @Input() bank!: Bank;

  constructor() { }

  ngOnInit() {
  }

}
