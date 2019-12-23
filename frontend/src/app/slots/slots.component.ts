import { Component, OnInit } from '@angular/core';
import { Slot } from '../interfaces/slot';
import {SlotService} from "../serviceSlot/slot.service";

@Component({
  selector: 'app-slots',
  templateUrl: './slots.component.html',
  styleUrls: ['./slots.component.scss']
})
export class SlotsComponent implements OnInit {

  slots : Slot[];

  constructor(public slotService: SlotService) { }

  ngOnInit() {
    this.getSlots();
  }

  getSlots(): void {
    this.slotService.getSlots()
    .subscribe(slots => this.slots = slots);
  }

}
